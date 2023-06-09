package pl.psk.upc.application.payment;

import pl.psk.upc.web.payment.PaymentDto;

import java.util.UUID;

public interface PaymentService {
    PaymentDto updateStatus(UUID paymentUuid);
}
