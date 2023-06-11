package pl.psk.upc.application.contract;

import org.springframework.stereotype.Service;
import pl.psk.upc.application.service.ServiceService;
import pl.psk.upc.exception.GenericNotFoundException;
import pl.psk.upc.infrastructure.entity.*;
import pl.psk.upc.infrastructure.repository.ClientRepository;
import pl.psk.upc.infrastructure.repository.ContractRepository;
import pl.psk.upc.infrastructure.repository.PaymentRepository;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.contract.ContractDto;
import pl.psk.upc.web.contract.ContractDtoWrapper;
import pl.psk.upc.web.product.ProductDto;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
class ContractServiceImpl implements ContractService {
    private final static String NOT_FOUND_MESSAGE = "Contract not found";

    private final ContractRepository contractRepository;
    private final PaymentRepository paymentRepository;
    private final ClientRepository clientRepository;
    private final ServiceService serviceService;

    public ContractServiceImpl(ContractRepository contractRepository, PaymentRepository paymentRepository, ClientRepository clientRepository, ServiceService serviceService) {
        this.contractRepository = contractRepository;
        this.paymentRepository = paymentRepository;
        this.clientRepository = clientRepository;
        this.serviceService = serviceService;
    }

    @Override
    public ContractDto addNewPaymentToContract(UUID contractUuid, double paymentAmount, String serviceName, List<ProductDto> products) {
        MethodArgumentValidator.requiredNotNull(contractUuid, "contractUuid");
        ContractEntity contract = contractRepository.findByUuid(contractUuid)
                .orElseThrow(() -> new GenericNotFoundException(NOT_FOUND_MESSAGE));
        List<PaymentEntity> payments = contract.getPaymentEntities();
        if (payments == null) {
            payments = new ArrayList<>();
        }
        String productsUuidList = products.stream()
                .map(ProductDto::getUuid)
                .map(UUID::toString)
                .collect(Collectors.joining(","));

        PaymentEntity newPayment = PaymentEntity.builder()
                .paymentStatus(PaymentStatus.NIEOPLACONE)
                .uuid(UUID.randomUUID())
                .amount(paymentAmount)
                .date(ZonedDateTime.now(ZoneId.systemDefault()))
                .contractEntity(contract)
                .name(serviceName)
                .productsUuid(productsUuidList)
                .build();
        PaymentEntity savedPayment = paymentRepository.save(newPayment);
        payments.add(savedPayment);

        contract.setPaymentEntities(payments);
        return ContractConverter.convertFrom(contractRepository.save(contract));
    }

    @Override
    public ContractDto findByUuid(UUID uuid) {
        MethodArgumentValidator.requiredNotNull(uuid, "uuid");
        return ContractConverter.convertFrom(contractRepository.findByUuid(uuid)
                .orElseThrow(() -> new GenericNotFoundException(NOT_FOUND_MESSAGE)));
    }

    @Override
    public ContractDtoWrapper findByUserUuid(UUID uuid) {
        MethodArgumentValidator.requiredNotNull(uuid, "uuid");
        ClientAccountEntity client = clientRepository.findByUuid(uuid)
                .orElseThrow(() -> new GenericNotFoundException(NOT_FOUND_MESSAGE));
        List<ContractEntity> contractEntities = client.getServices().stream()
                .map(ServiceEntity::getContractEntity)
                .toList();
        return ContractConverter.convertFrom(contractEntities);
    }

    @Override
    public ContractDto findByServiceUuid(UUID uuid) {
        MethodArgumentValidator.requiredNotNull(uuid, "uuid");
        return serviceService.getService(uuid)
                .getContract();
    }
}
