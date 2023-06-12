package pl.psk.upc.application.payment;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.psk.upc.application.client.ClientConverter;
import pl.psk.upc.application.client.ClientService;
import pl.psk.upc.application.product.ProductService;
import pl.psk.upc.application.service.ServiceService;
import pl.psk.upc.exception.GenericNotFoundException;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.PaymentEntity;
import pl.psk.upc.infrastructure.entity.PaymentStatus;
import pl.psk.upc.infrastructure.repository.ClientRepository;
import pl.psk.upc.infrastructure.repository.PaymentRepository;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.payment.InvoiceDto;
import pl.psk.upc.web.payment.InvoiceDtoWrapper;
import pl.psk.upc.web.payment.PaymentDto;
import pl.psk.upc.web.payment.PaymentDtoWrapper;
import pl.psk.upc.web.product.ProductDto;
import pl.psk.upc.web.service.ServiceDto;
import pl.psk.upc.web.user.ClientDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
class PaymentServiceImpl implements PaymentService {
    private final static String NOT_FOUND_MESSAGE = "Payment not found";

    private final PaymentRepository paymentRepository;
    private final ClientRepository clientRepository;
    private final ProductService productService;
    private final ServiceService serviceService;

    public PaymentServiceImpl(PaymentRepository paymentRepository, ClientRepository clientRepository, ProductService productService, ServiceService serviceService) {
        this.paymentRepository = paymentRepository;
        this.clientRepository = clientRepository;
        this.productService = productService;
        this.serviceService = serviceService;
    }

    @Override
    public PaymentDto updateStatus(UUID uuid, UUID clientUuid) {
        MethodArgumentValidator.requiredNotNull(uuid, "uuid");
        PaymentEntity payment = paymentRepository.findByUuid(uuid)
                .orElseThrow(() -> new GenericNotFoundException(NOT_FOUND_MESSAGE));
        payment.setPaymentStatus(PaymentStatus.OPLACONE);
        ClientAccountEntity client = clientRepository.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        client.setBalance(client.getBalance() + payment.getAmount());
        clientRepository.save(client);
        return PaymentConverter.convertFrom(paymentRepository.save(payment));
    }

    @Override
    public PaymentDtoWrapper findAll() {
        return PaymentConverter.convertFrom(paymentRepository.findAll());
    }

    @Override
    public InvoiceDtoWrapper findAllByUser(UUID uuid) {
        MethodArgumentValidator.requiredNotNull(uuid, "uuid");
        ClientAccountEntity client = clientRepository.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return getInvoices(ClientConverter.convertFrom(client));
    }

    private InvoiceDtoWrapper getInvoices(ClientDto client) {
        List<InvoiceDto> result = new ArrayList<>();
        for (ServiceDto s : client.getServices()) {
            result.addAll(fromPayments(s.getContract().getPayments(), client));
        }
        return InvoiceDtoWrapper.builder()
                .content(result)
                .build();
    }

    private List<InvoiceDto> fromPayments(List<PaymentDto> payments, ClientDto client) {
        List<InvoiceDto> result = new ArrayList<>();
        for (PaymentDto p : payments) {
            List<ProductDto> products = new ArrayList<>();

            if (p.getProductDtosUuids() != null && StringUtils.isNotBlank(p.getProductDtosUuids())) {
                products = Arrays.stream(p.getProductDtosUuids().split(","))
                        .map(UUID::fromString)
                        .map(productService::getProduct)
                        .toList();
            }

            result.add(InvoiceDto.builder()
                    .uuid(p.getUuid())
                    .name(p.getName())
                    .amount(p.getAmount())
                    .creationDate(p.getDate())
                    .paymentDate(p.getDate().plusDays(14L))
                    .paymentStatus(p.getPaymentStatus())
                    .productDtos(products)
                    .clientFirstName(client.getFirstName())
                    .clientLastName(client.getLastName())
                    .clientAddress(client.getAddress())
                    .clientPesel(client.getPesel())
//                    .service(serviceService.getService(UUID.fromString(p.getServiceUuid())))
                    .build());
        }
        return result;
    }
}
