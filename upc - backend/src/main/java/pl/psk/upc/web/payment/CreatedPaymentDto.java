package pl.psk.upc.web.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
public class CreatedPaymentDto {

    private final static String PAYMENT_UUID = "paymentUuid";
    private final static String LINK = "link";

    @JsonProperty(PAYMENT_UUID)
    UUID paymentUuid;

    @JsonProperty(LINK)
    String link;

    @Builder
    public CreatedPaymentDto(@JsonProperty(PAYMENT_UUID) UUID paymentUuid, @JsonProperty(LINK) String link) {
        this.paymentUuid = paymentUuid;
        this.link = link;
    }
}
