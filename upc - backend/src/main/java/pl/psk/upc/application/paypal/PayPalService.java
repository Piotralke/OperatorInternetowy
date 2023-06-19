package pl.psk.upc.application.paypal;

import com.paypal.base.rest.PayPalRESTException;
import pl.psk.upc.web.payment.CreatedPaymentDto;
import pl.psk.upc.web.payment.PaymentInputDto;

import java.util.UUID;

public interface PayPalService {
    CreatedPaymentDto createPayment(PaymentInputDto inputDto) throws PayPalRESTException;
    CreatedPaymentDto createPayPalPaymentForExistingPayment( PaymentInputDto inputDto) throws PayPalRESTException;
    void executePayment(String paymentId, String payerId, UUID orderUuid, UUID paymentUuid, UUID clientUuid) throws PayPalRESTException;
}
