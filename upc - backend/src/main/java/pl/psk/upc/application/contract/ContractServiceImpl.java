package pl.psk.upc.application.contract;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.entity.ContractEntity;
import pl.psk.upc.infrastructure.entity.PaymentEntity;
import pl.psk.upc.infrastructure.entity.PaymentStatus;
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

    public ContractServiceImpl(ContractRepository contractRepository, PaymentRepository paymentRepository) {
        this.contractRepository = contractRepository;
        this.paymentRepository = paymentRepository;
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
        PaymentEntity savedPayment = paymentRepository.save(newPayment);
        payments.add(savedPayment);

        contract.setPaymentEntities(payments);
        ContractEntity updatedEntity = contractRepository.save(contract);
        return ContractConverter.convertFrom(updatedEntity);
    }
}
