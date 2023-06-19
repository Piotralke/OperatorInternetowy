package pl.psk.upc.application.payment;

import pl.psk.upc.infrastructure.entity.PaymentEntity;
import pl.psk.upc.infrastructure.enums.PaymentStatus;
import pl.psk.upc.web.payment.InvoiceDtoWrapper;
import pl.psk.upc.web.payment.PaymentDto;
import pl.psk.upc.web.payment.PaymentDtoWrapper;

import java.util.List;
import java.util.UUID;

public interface PaymentService {
    PaymentDto updateStatus(UUID paymentUuid, UUID clientUuid);
    PaymentDto updateStatus(UUID paymentUuid, UUID clientUuid, PaymentStatus paymentStatus);
    PaymentDtoWrapper findAll();
    InvoiceDtoWrapper findAllByUser(UUID uuid);
    PaymentDto findByUuid(UUID uuid);
    List<PaymentEntity> getAllNotPayedPayments();
}
