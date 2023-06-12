package pl.psk.upc.web.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Adres e-mail jest wymagany")
    @Email(message = "Niepoprawny adres e-mail")
    @JsonProperty(CLIENT_EMAIL)
    String clientEmail;

    @NotBlank(message = "Adres e-mail jest wymagany")
    @Email(message = "Niepoprawny adres e-mail")
    @JsonProperty(EMPLOYEE_EMAIL)
    String employeeEmail;

    @JsonProperty(PRODUCT_UUIDS)
    List<UUID> productUuids;

    @NotNull
    @JsonProperty(OFFER_UUID)
    UUID offerUuid;

    @NotNull
    @JsonProperty(CONTRACT_LENGTH)
    ContractLengthEnum contractLength;
}
