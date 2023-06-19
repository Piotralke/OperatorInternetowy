package pl.psk.upc.web.payment;

import com.paypal.base.rest.PayPalRESTException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.psk.upc.application.payment.InvoiceService;
import pl.psk.upc.application.payment.PaymentService;
import pl.psk.upc.application.paypal.PayPalService;
import pl.psk.upc.infrastructure.enums.OrderStatus;
import pl.psk.upc.infrastructure.enums.PaymentStatus;
import pl.psk.upc.web.UpcRestPaths;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class PaymentController {

    private final PayPalService payPalService;
    private final PaymentService paymentService;
    private final InvoiceService invoiceService;

    public PaymentController(PayPalService payPalService, PaymentService paymentService, InvoiceService invoiceService) {
        this.payPalService = payPalService;
        this.paymentService = paymentService;
        this.invoiceService = invoiceService;
    }

    @GetMapping(UpcRestPaths.PAYMENT_STATUSES)
    public List<PaymentStatus> getPaymentStatuses() {
        return Arrays.stream(PaymentStatus.values())
                .toList();
    }

    @PostMapping(UpcRestPaths.CREATE_PAYMENT)
    public CreatedPaymentDto createPayment(@RequestBody PaymentInputDto inputDto) throws PayPalRESTException {
        return payPalService.createPayment(inputDto);
    }

    @PostMapping(UpcRestPaths.CREATE_PAYMENT_FOR_EXISTING_PAYMENT)
    public CreatedPaymentDto createPayPalPaymentForExistingPayment(@RequestBody PaymentInputDto inputDto) throws PayPalRESTException {
        return payPalService.createPayPalPaymentForExistingPayment(inputDto);
    }

    @GetMapping(UpcRestPaths.GET_INVOICES_BY_CLIENT_UUID)
    public InvoiceDtoWrapper getInvoicesByUser(@PathVariable UUID uuid) {
        return paymentService.findAllByUser(uuid);
    }

    @GetMapping(UpcRestPaths.GET_INVOICE_AS_PDF)
    public void generateInvoiceAsPdf(@RequestParam UUID clientUuid, @RequestParam UUID paymentUuid, HttpServletResponse response) throws IOException {
        ResourceRegion resourceRegion = invoiceService.generateInvoice(clientUuid, paymentUuid);
        Resource resource = resourceRegion.getResource();
        String contentType = MediaTypeFactory.getMediaType(resource)
                .orElse(MediaType.APPLICATION_OCTET_STREAM).toString();
        response.setContentType(contentType);
        response.setContentLengthLong(resource.contentLength());

        try (InputStream i = resource.getInputStream()) {
            i.transferTo(response.getOutputStream());
        }
    }

    @PostMapping(UpcRestPaths.EXECUTE_PAYMENT)
    public ResponseEntity<String> executePayment(@PathVariable String paymentId, @RequestParam("payerId") String payerId, @RequestParam(value = "orderUuid", required = false) UUID orderUuid, @RequestParam(value = "paymentUuid") UUID paymentUuid, @RequestParam UUID clientUuid) throws PayPalRESTException {
        try {
            payPalService.executePayment(paymentId, payerId, orderUuid, paymentUuid, clientUuid);
            return ResponseEntity.ok("Payment executed successfully!");
        } catch (PayPalRESTException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while executing payment: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid payment: " + e.getMessage());
        }
    }

    @PutMapping(UpcRestPaths.EDIT_PAYMENT_STATUS)
    public UUID editPaymentStatus(@PathVariable(value = "uuid") UUID uuid, @RequestParam PaymentStatus paymentStatus,
                                  @RequestParam UUID clientUuid) {
        return paymentService.updateStatus(uuid, clientUuid, paymentStatus)
                .getUuid();
    }

}
