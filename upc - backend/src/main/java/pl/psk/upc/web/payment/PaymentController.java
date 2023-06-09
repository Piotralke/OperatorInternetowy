package pl.psk.upc.web.payment;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;
import pl.psk.upc.application.payment.PaymentService;
import pl.psk.upc.web.UpcRestPaths;
import pl.psk.upc.web.order.OrderDto;

import java.io.IOException;
import java.util.UUID;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(UpcRestPaths.CREATE_PAYMENT)
    public String createPayment(@RequestBody PaymentInputDto inputDto) throws PayPalRESTException {
        return paymentService.createPayment(inputDto);
    }

    @PostMapping(UpcRestPaths.EXECUTE_PAYMENT)
    public OrderDto executePayment(@PathVariable String paymentId, @RequestParam("payerId") String payerId, @RequestParam("orderUuid") UUID orderUuid) throws PayPalRESTException {
//        try {
        return paymentService.executePayment(paymentId, payerId, orderUuid);
//            return ResponseEntity.ok("Payment executed successfully!");
//        } catch (PayPalRESTException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while executing payment: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid payment: " + e.getMessage());
//        }
    }

}
