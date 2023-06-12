package pl.psk.upc.application.payment;

import pl.psk.upc.web.payment.InvoiceDtoWrapper;
import pl.psk.upc.web.payment.PaymentDto;
import pl.psk.upc.web.payment.PaymentDtoWrapper;

import java.util.UUID;

public interface PaymentService {
    PaymentDto updateStatus(UUID paymentUuid, UUID clientUuid);
    PaymentDtoWrapper findAll();
    InvoiceDtoWrapper findAllByUser(UUID uuid);
}
