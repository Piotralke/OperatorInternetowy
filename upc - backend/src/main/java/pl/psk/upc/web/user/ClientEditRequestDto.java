package pl.psk.upc.web.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Value;

@Value
public class ClientEditRequestDto {

    private final static String UUID = "uuid";
    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "lastName";
    private final static String ADDRESS = "address";
    private final static String PASSWORD = "password";
    private final static String BALANCE = "balance";
    private final static String PHONE_NUMBER = "phoneNumber";
    private final static String NIP = "nip";
    private final static String IS_BUSINESS_CLIENT = "isBusinessClient";

    @NotNull
    @JsonProperty(UUID)
    java.util.UUID uuid;

    @NotBlank
    @JsonProperty(FIRST_NAME)
    String firstName;

    @NotBlank
    @JsonProperty(LAST_NAME)
    String lastName;

    @NotBlank(message = "Hasło jest wymagane")
    @Size(min = 8, message = "Hasło musi mieć co najmniej 8 znaków")
    @JsonProperty(PASSWORD)
    String password;

    @NotBlank
    @JsonProperty(ADDRESS)
    String address;

    @Positive
    @JsonProperty(BALANCE)
    double balance;

    @Pattern(regexp = "\\d{9}", message = "Numer telefonu musi składać się z 9 cyfr")
    @JsonProperty(PHONE_NUMBER)
    String phoneNumber;

    @Pattern(regexp = "\\d{10}", message = "NIP musi składać się z 10 cyfr")
    @JsonProperty(NIP)
    String nip;

    @JsonProperty(IS_BUSINESS_CLIENT)
    boolean isBusinessClient;

    @Builder
    public ClientEditRequestDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(FIRST_NAME) String firstName, @JsonProperty(LAST_NAME)String lastName, @JsonProperty(ADDRESS) String address,
                     @JsonProperty(BALANCE) double balance, @JsonProperty(PASSWORD) String password, @JsonProperty(PHONE_NUMBER) String phoneNumber, @JsonProperty(NIP) String nip,
                                @JsonProperty(IS_BUSINESS_CLIENT) boolean isBusinessClient) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.balance = balance;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.nip = nip;
        this.isBusinessClient = isBusinessClient;
    }
}
