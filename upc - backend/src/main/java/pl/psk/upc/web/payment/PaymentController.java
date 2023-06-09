package pl.psk.upc.web.payment;

import com.paypal.base.rest.PayPalRESTException;
import org.springframework.web.bind.annotation.*;
import pl.psk.upc.application.paypal.PayPalService;
import pl.psk.upc.web.UpcRestPaths;
import pl.psk.upc.web.order.OrderDto;

import java.util.UUID;

@RestController
public class PaymentController {

    private final PayPalService payPalService;

    public PaymentController(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @GetMapping(UpcRestPaths.CREATE_PAYMENT)
    public String createPayment(@RequestBody PaymentInputDto inputDto) throws PayPalRESTException {
        return payPalService.createPayment(inputDto);
    }

    @PostMapping(UpcRestPaths.EXECUTE_PAYMENT)
    public OrderDto executePayment(@PathVariable String paymentId, @RequestParam("payerId") String payerId, @RequestParam(value = "orderUuid", required = false) UUID orderUuid, @RequestParam(value = "paymentUuid", required = false) UUID paymentUuid) throws PayPalRESTException {
//        try {
        return payPalService.executePayment(paymentId, payerId, orderUuid, paymentUuid);
//            return ResponseEntity.ok("Payment executed successfully!");
//        } catch (PayPalRESTException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while executing payment: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid payment: " + e.getMessage());
//        }
    }

}
