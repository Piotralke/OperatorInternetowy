package pl.psk.upc.application.order;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.psk.upc.infrastructure.entity.*;
import pl.psk.upc.infrastructure.repository.*;
import pl.psk.upc.web.order.OrderDto;
import pl.psk.upc.web.order.OrderDtoWrapper;
import pl.psk.upc.web.order.OrderInputDto;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final OrderRepository orderRepository;
    private final OfferRepository offerRepository;
    private final ProductRepository productRepository;
    private final ContractRepository contractRepository;
    private final ServiceRepository serviceRepository;

    public OrderServiceImpl(ClientRepository clientRepository, EmployeeRepository employeeRepository, OrderRepository orderRepository, OfferRepository offerRepository, ProductRepository productRepository, ContractRepository contractRepository, ServiceRepository serviceRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.orderRepository = orderRepository;
        this.offerRepository = offerRepository;
        this.productRepository = productRepository;
        this.contractRepository = contractRepository;
        this.serviceRepository = serviceRepository;
    }

    @Override
    public OrderDtoWrapper getOrdersByClient(UUID clientUuid) {
        ClientAccountEntity clientAccountEntity = clientRepository.findByUuid(clientUuid)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return OrderConverter.convertFrom(clientAccountEntity.getOrderEntities());
    }

    @Override
    public OrderDtoWrapper getOrdersByClient(String email) {
        ClientAccountEntity clientAccountEntity = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return OrderConverter.convertFrom(clientAccountEntity.getOrderEntities());
    }

    @Override
    public OrderDto getOrderByUuid(UUID uuid) {
        return OrderConverter.convertFrom(orderRepository.getByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Order not found")));
    }

    @Override
    public OrderDtoWrapper getOrdersByEmployee(UUID employeeUuid) {
        EmployeeEntity employee = employeeRepository.findByUuid(employeeUuid)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<OrderEntity> orders = orderRepository.getOrdersByEmployeeEntity(employee);
        return OrderConverter.convertFrom(orders);
    }

    @Override
    public OrderDtoWrapper getOrdersByEmployee(String email) {
        EmployeeEntity employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<OrderEntity> orders = orderRepository.getOrdersByEmployeeEntity(employee);
        return OrderConverter.convertFrom(orders);
    }

    @Transactional
    @Override
    public UUID saveOrder(OrderInputDto order) {
        ClientAccountEntity clientAccountEntity = clientRepository.findByEmail(order.getClientEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        EmployeeEntity employeeEntity = null;
        if(StringUtils.isNotBlank(order.getEmployeeEmail())) {
            employeeEntity = employeeRepository.findByEmail(order.getEmployeeEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        }
        OfferEntity offer = offerRepository.findByUuid(order.getOfferUuid());

        List<ProductEntity> products = order.getProductUuids().stream()
                .map(productRepository::findByUuid)
                .toList();

        ZonedDateTime contractStartDate = ZonedDateTime.now(ZoneId.systemDefault());

        ContractEntity serviceContract = ContractEntity.builder()
                .uuid(UUID.randomUUID())
                .startDate(contractStartDate)
                .endDate(getDateOfContractEnd(contractStartDate, order.getContractLength()))
                .amount(calculateOrderAmount(offer, products))
                .offerEntity(offer)
                .clientAccountEntity(clientAccountEntity)
                .build();

        ContractEntity savedServiceContract = contractRepository.save(serviceContract);

        ServiceEntity service = ServiceEntity.builder()
                .uuid(UUID.randomUUID())
                .name(offer.getName())
                .offerType(offer.getOfferType())
                .clientAccountEntity(clientAccountEntity)
                .contractEntity(savedServiceContract)
                .build();

        ServiceEntity savedService = serviceRepository.save(service);

        OrderEntity orderToSave = OrderEntity.builder()
                .uuid(UUID.randomUUID())
                .orderDate(ZonedDateTime.now(ZoneId.systemDefault()))
                .orderStatus(OrderStatus.PRZYJETE)
                .paymentStatus(PaymentStatus.NIEOPLACONE)
                .employeeEntity(employeeEntity)
                .productEntities(products)
                .service(savedService)
                .build();

        return orderRepository.save(orderToSave)
                .getUuid();
    }

    private Double calculateOrderAmount(OfferEntity offer, List<ProductEntity> products) {
        Double result = 0.0;
        List<Double> productsPriceList = products.stream()
                .map(ProductEntity::getPrice)
                .toList();
        for (Double price : productsPriceList) {
            result += price;
        }
        result += offer.getPrice();
        return result;
    }

    private ZonedDateTime getDateOfContractEnd(ZonedDateTime startDate, ContractLengthEnum contractLength) {
        return startDate.plusMonths(contractLength.getContractLengthAsNumber());
    }
}
