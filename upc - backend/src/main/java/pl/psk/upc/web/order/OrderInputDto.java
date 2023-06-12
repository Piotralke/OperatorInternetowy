package pl.psk.upc.web.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import pl.psk.upc.infrastructure.enums.ContractLengthEnum;

import java.util.List;
import java.util.UUID;

@Value
public class OrderInputDto {

    private final static String CLIENT_EMAIL = "clientEmail";
    private final static String EMPLOYEE_EMAIL = "employeeEmail";
    private final static String PRODUCT_UUIDS = "productUuids";
    private final static String OFFER_UUID = "offerUuid";
    private final static String CONTRACT_LENGTH = "contractLength";

    @JsonProperty(CLIENT_EMAIL)
    String clientEmail;

    @JsonProperty(EMPLOYEE_EMAIL)
    String employeeEmail;

    @JsonProperty(PRODUCT_UUIDS)
    List<UUID> productUuids;

    @JsonProperty(OFFER_UUID)
    UUID offerUuid;

    @JsonProperty(CONTRACT_LENGTH)
    ContractLengthEnum contractLength;
}
