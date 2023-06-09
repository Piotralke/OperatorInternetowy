package pl.psk.upc.application.contract;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.entity.ContractEntity;
import pl.psk.upc.infrastructure.entity.PaymentEntity;
import pl.psk.upc.infrastructure.entity.PaymentStatus;
import pl.psk.upc.infrastructure.repository.ClientRepository;
import pl.psk.upc.infrastructure.repository.ContractRepository;
import pl.psk.upc.infrastructure.repository.PaymentRepository;
import pl.psk.upc.web.contract.ContractDto;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final PaymentRepository paymentRepository;
    private final ClientRepository clientRepository;

    public ContractServiceImpl(ContractRepository contractRepository, PaymentRepository paymentRepository, ClientRepository clientRepository) {
        this.contractRepository = contractRepository;
        this.paymentRepository = paymentRepository;
        this.clientRepository = clientRepository;
    }


    @Override
    public ContractDto addNewPaymentToContract(UUID contractUuid, double paymentAmount) {
        ContractEntity contract = contractRepository.findByUuid(contractUuid)
                .orElseThrow(() -> new UsernameNotFoundException("Contract not fount"));
        List<PaymentEntity> payments = contract.getPaymentEntities();
        if (payments == null) {
            payments = new ArrayList<>();
        }

        PaymentEntity newPayment = PaymentEntity.builder()
                .paymentStatus(PaymentStatus.NIEOPLACONE)
                .uuid(UUID.randomUUID())
                .amount(paymentAmount)
                .date(ZonedDateTime.now(ZoneId.systemDefault()))
                .build();

        payments.add(newPayment);

        contract.setPaymentEntities(payments);
        return ContractConverter.convertFrom(contractRepository.save(contract));
    }

    @Override
    public ContractDto findByUuid(UUID uuid) {
        return ContractConverter.convertFrom(contractRepository.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("Not found")));
    }
}
