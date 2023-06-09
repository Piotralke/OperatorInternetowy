package pl.psk.upc.web.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.infrastructure.entity.PaymentStatus;

import java.time.ZonedDateTime;

@Value
public class PaymentDto {

    private final static String UUID = "uuid";
    private final static String DATE = "date";
    private final static String AMOUNT = "amount";
    private final static String PAYMENT_STATUS = "paymentStatus";

    @JsonProperty(UUID)
    java.util.UUID uuid;

    @JsonProperty(DATE)
    ZonedDateTime date;

    @JsonProperty(AMOUNT)
    double amount;

    @JsonProperty(PAYMENT_STATUS)
    PaymentStatus paymentStatus;

    @Builder
    public PaymentDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(DATE) ZonedDateTime date, @JsonProperty(AMOUNT) double amount,
                      @JsonProperty(PAYMENT_STATUS) PaymentStatus paymentStatus) {
        this.uuid = uuid;
        this.date = date;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
    }
}
