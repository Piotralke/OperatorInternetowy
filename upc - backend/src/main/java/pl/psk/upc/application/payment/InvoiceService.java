package pl.psk.upc.application.payment;

import org.springframework.core.io.support.ResourceRegion;

import java.util.UUID;

public interface InvoiceService {
    ResourceRegion generateInvoice(UUID clientUuid, UUID paymentUuid);
}
