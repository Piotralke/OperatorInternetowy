package pl.psk.upc.web.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.infrastructure.entity.PaymentStatus;
import pl.psk.upc.web.product.ProductDto;

import java.time.ZonedDateTime;
import java.util.List;

@Value
public class PaymentDto {

    private final static String UUID = "uuid";
    private final static String NAME = "name";
    private final static String DATE = "date";
    private final static String AMOUNT = "amount";
    private final static String PRODUCTS = "productDtosUuids";
    private final static String PAYMENT_STATUS = "paymentStatus";
    private final static String SERVICE_UUID = "serviceUuid";

    @JsonProperty(UUID)
    java.util.UUID uuid;

    @JsonProperty(DATE)
    ZonedDateTime date;

    @JsonProperty(NAME)
    String name;

    @JsonProperty(AMOUNT)
    double amount;

    @JsonProperty(PRODUCTS)
    String productDtosUuids;

    @JsonProperty(PAYMENT_STATUS)
    PaymentStatus paymentStatus;

    @JsonProperty(SERVICE_UUID)
    String serviceUuid;

    @Builder
    public PaymentDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(DATE) ZonedDateTime date, @JsonProperty(AMOUNT) double amount,
                      @JsonProperty(PAYMENT_STATUS) PaymentStatus paymentStatus, @JsonProperty(NAME) String name, @JsonProperty(PRODUCTS) String productDtosUuids,
                      @JsonProperty(SERVICE_UUID) String serviceUuid) {
        this.uuid = uuid;
        this.date = date;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.name = name;
        this.productDtosUuids = productDtosUuids;
        this.serviceUuid = serviceUuid;
    }
}
