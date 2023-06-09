package pl.psk.upc.application.payment;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.entity.PaymentEntity;
import pl.psk.upc.infrastructure.entity.PaymentStatus;
import pl.psk.upc.infrastructure.repository.PaymentRepository;
import pl.psk.upc.web.payment.PaymentDto;
import pl.psk.upc.web.payment.PaymentDtoWrapper;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentDto updateStatus(UUID uuid) {
        PaymentEntity payment = paymentRepository.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("Payment not found"));
        payment.setPaymentStatus(PaymentStatus.OPLACONE);
        return PaymentConverter.convertFrom(paymentRepository.save(payment));
    }

    @Override
    public PaymentDtoWrapper findAll() {
        return PaymentConverter.convertFrom(paymentRepository.findAll());
    }
}
