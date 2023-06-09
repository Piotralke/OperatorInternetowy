package pl.psk.upc.application.order;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.psk.upc.exception.GenericException;
import pl.psk.upc.exception.GenericNotFoundException;
import pl.psk.upc.infrastructure.entity.*;
import pl.psk.upc.infrastructure.enums.ContractLengthEnum;
import pl.psk.upc.infrastructure.enums.OrderStatus;
import pl.psk.upc.infrastructure.enums.PaymentStatus;
import pl.psk.upc.infrastructure.repository.*;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.order.OrderDto;
import pl.psk.upc.web.order.OrderDtoWrapper;
import pl.psk.upc.web.order.OrderInputDto;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
class OrderServiceImpl implements OrderService {
    private final static String NOT_FOUND_MESSAGE = "Order not found";
    private final static String USER_NOT_FOUND_MESSAGE = "User not found";

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
        MethodArgumentValidator.requiredNotNull(clientUuid, "clientUuid");
        ClientAccountEntity clientAccountEntity = clientRepository.findByUuid(clientUuid)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));
        List<OrderEntity> ordersByClientAccountEntity = orderRepository.getOrdersByClientAccountEntity(clientAccountEntity);
        return OrderConverter.convertFrom(ordersByClientAccountEntity);
    }

    @Override
    public OrderDtoWrapper getOrdersByClient(String email) {
        MethodArgumentValidator.requiredNotNullOrBlankString(email, "email");
        ClientAccountEntity clientAccountEntity = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));
        List<OrderEntity> ordersByClientAccountEntity = orderRepository.getOrdersByClientAccountEntity(clientAccountEntity);
        return OrderConverter.convertFrom(ordersByClientAccountEntity);
    }

    @Override
    public OrderDto getOrderByUuid(UUID uuid) {
        MethodArgumentValidator.requiredNotNull(uuid, "uuid");
        return OrderConverter.convertFrom(orderRepository.findByUuid(uuid)
                .orElseThrow(() -> new GenericNotFoundException(NOT_FOUND_MESSAGE)));
    }

    @Override
    public OrderDtoWrapper getOrdersByEmployee(UUID employeeUuid) {
        MethodArgumentValidator.requiredNotNull(employeeUuid, "employeeUuid");
        EmployeeEntity employee = employeeRepository.findByUuid(employeeUuid)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));
        List<OrderEntity> orders = orderRepository.getOrdersByEmployeeEntity(employee);
        return OrderConverter.convertFrom(orders);
    }

    @Override
    public OrderDtoWrapper getOrdersByEmployee(String email) {
        MethodArgumentValidator.requiredNotNullOrBlankString(email, "email");
        EmployeeEntity employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));
        List<OrderEntity> orders = orderRepository.getOrdersByEmployeeEntity(employee);
        return OrderConverter.convertFrom(orders);
    }

    @Transactional
    @Override
    public UUID saveOrder(OrderInputDto order) {
        MethodArgumentValidator.requiredNotNull(order, "order");
        ClientAccountEntity clientAccountEntity = clientRepository.findByEmail(order.getClientEmail())
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));
        EmployeeEntity employeeEntity = null;
        if (StringUtils.isNotBlank(order.getEmployeeEmail())) {
            employeeEntity = employeeRepository.findByEmail(order.getEmployeeEmail())
                    .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));
        }
        OfferEntity offer = offerRepository.findByUuid(order.getOfferUuid())
                .orElseThrow(() -> new GenericNotFoundException(NOT_FOUND_MESSAGE));

        List<ProductEntity> products = null;

        if (order.getProductUuids() == null) {
            products = new ArrayList<>();
        } else {
            products = order.getProductUuids().stream()
                    .map(productRepository::findByUuid)
                    .map(productEntity -> productEntity.orElseThrow(() -> new GenericNotFoundException(NOT_FOUND_MESSAGE)))
                    .toList();
        }

        ZonedDateTime contractStartDate = ZonedDateTime.now(ZoneId.systemDefault());

        ContractEntity serviceContract = ContractEntity.builder()
                .uuid(UUID.randomUUID())
                .startDate(contractStartDate)
                .endDate(getDateOfContractEnd(contractStartDate, order.getContractLength()))
                .amount(offer.getPrice())
                .offerEntity(offer)
                .build();

        ContractEntity savedServiceContract = contractRepository.save(serviceContract);

        ServiceEntity service = ServiceEntity.builder()
                .uuid(UUID.randomUUID())
                .name(offer.getName())
                .offerType(offer.getOfferType())
                .contractEntity(savedServiceContract)
                .clientAccountEntity(clientAccountEntity)
                .build();

        ServiceEntity savedService = serviceRepository.save(service);

        OrderEntity orderToSave = OrderEntity.builder()
                .uuid(UUID.randomUUID())
                .orderDate(ZonedDateTime.now(ZoneId.systemDefault()))
                .orderStatus(OrderStatus.PRZYJETE)
                .paymentStatus(PaymentStatus.NIEOPLACONE)
                .amount(calculateOrderAmount(offer, products))
                .employeeEntity(employeeEntity)
                .productEntities(products)
                .service(savedService)
                .clientAccountEntity(clientAccountEntity)
                .build();

        OrderEntity savedOrder = orderRepository.save(orderToSave);

        clientRepository.save(clientAccountEntity);

        return savedOrder.getUuid();
    }

    @Override
    public OrderDto updatePaymentStatusInOrder(UUID orderUuid) {
        MethodArgumentValidator.requiredNotNull(orderUuid, "orderUuid");
        OrderEntity order = orderRepository.findByUuid(orderUuid)
                .orElseThrow(() -> new GenericNotFoundException(NOT_FOUND_MESSAGE));
        order.setPaymentStatus(PaymentStatus.OPLACONE);

        ContractEntity contractEntity = order.getService()
                .getContractEntity();
        List<PaymentEntity> paymentEntity = contractEntity.getPaymentEntities();
        List<ZonedDateTime> sortedDates = paymentEntity.stream()
                .map(PaymentEntity::getDate)
                .sorted()
                .toList();
        ZonedDateTime lastPaymentDate = sortedDates.get(sortedDates.size() - 1);
        for (PaymentEntity p : paymentEntity) {
            if (p.getDate().equals(lastPaymentDate)) {
                p.setPaymentStatus(PaymentStatus.OPLACONE);
            }
        }
        contractEntity.setPaymentEntities(paymentEntity);
        contractRepository.save(contractEntity);
        return OrderConverter.convertFrom(orderRepository.save(order));
    }

    @Override
    public UUID updateOrderStatus(UUID orderUuid, OrderStatus orderStatus) {
        MethodArgumentValidator.requiredNotNull(orderUuid, "orderUuid");
        OrderEntity order = orderRepository.findByUuid(orderUuid)
                .orElseThrow(() -> new GenericNotFoundException(NOT_FOUND_MESSAGE));

        if (orderStatus.equals(OrderStatus.ZAKONCZONE) && order.getPaymentStatus() != PaymentStatus.OPLACONE) {
            throw new GenericException("The order has not been paid");
        }

        order.setOrderStatus(orderStatus);
        OrderEntity savedOrder = orderRepository.save(order);
        return savedOrder.getUuid();
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

        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String resultAsString = df.format(result).replace(",", ".");
        return Double.parseDouble(resultAsString);
    }

    private ZonedDateTime getDateOfContractEnd(ZonedDateTime startDate, ContractLengthEnum contractLength) {
        return startDate.plusMonths(contractLength.getContractLengthAsNumber());
    }
}