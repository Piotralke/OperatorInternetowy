package pl.psk.upc.web.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.UUID;

@Value
public class PaymentInputDto {

    private final static String ORDER_UUID = "orderUuid";
    private final static String CLIENT_UUID = "clientUuid";
    private final static String SERVICE_UUID = "serviceUuid";
    private final static String SUCCESS_URL = "successUrl";
    private final static String CANCEL_URL = "cancelUrl";

    @JsonProperty(CLIENT_UUID)
    UUID clientUuid;

    @JsonProperty(ORDER_UUID)
    UUID orderUuid;

    @JsonProperty(SERVICE_UUID)
    UUID serviceUuid;

    @JsonProperty(SUCCESS_URL)
    String successUrl;

    @JsonProperty(CANCEL_URL)
    String cancelUrl;

    public PaymentInputDto(@JsonProperty(SERVICE_UUID) UUID serviceUuid, @JsonProperty(CLIENT_UUID) UUID clientUuid, @JsonProperty(ORDER_UUID) UUID orderUuid, @JsonProperty(SUCCESS_URL) String successUrl, @JsonProperty(CANCEL_URL) String cancelUrl) {
        this.serviceUuid = serviceUuid;
        this.clientUuid = clientUuid;
        this.orderUuid = orderUuid;
        this.successUrl = successUrl;
        this.cancelUrl = cancelUrl;
    }
}
