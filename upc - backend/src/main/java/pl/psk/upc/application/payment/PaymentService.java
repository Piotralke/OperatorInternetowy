package pl.psk.upc.application.payment;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import pl.psk.upc.web.order.OrderDto;
import pl.psk.upc.web.payment.PaymentInputDto;

import java.util.UUID;

public interface PaymentService {
    String createPayment(PaymentInputDto inputDto) throws PayPalRESTException;
    OrderDto executePayment(String paymentId, String payerId, UUID orderUuid) throws PayPalRESTException;
}
