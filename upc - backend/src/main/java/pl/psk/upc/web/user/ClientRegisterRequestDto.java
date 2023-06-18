package pl.psk.upc.web.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Value;

@Value
public class ClientRegisterRequestDto {

    private final static String UUID = "uuid";
    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "lastName";
    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";
    private final static String ADDRESS = "address";
    private final static String BALANCE = "balance";
    private final static String PHONE_NUMBER = "phoneNumber";
    private final static String NIP = "nip";
    private final static String PESEL = "pesel";
    private final static String IS_BUSINESS_CLIENT = "isBusinessClient";

    @NotBlank
    @JsonProperty(UUID)
    String uuid;

    @NotBlank
    @JsonProperty(FIRST_NAME)
    String firstName;

    @NotBlank
    @JsonProperty(LAST_NAME)
    String lastName;

    @NotBlank(message = "Adres e-mail jest wymagany")
    @Email(message = "Niepoprawny adres e-mail")
    @JsonProperty(EMAIL)
    String email;

    @NotBlank(message = "Hasło jest wymagane")
    @Size(min = 8, message = "Hasło musi mieć co najmniej 8 znaków")
    @JsonProperty(PASSWORD)
    String password;

    @NotBlank
    @JsonProperty(ADDRESS)
    String address;

    @JsonProperty(BALANCE)
    double balance;

    @Pattern(regexp = "\\d{9}", message = "Numer telefonu musi składać się z 9 cyfr")
    @JsonProperty(PHONE_NUMBER)
    String phoneNumber;

    @JsonProperty(NIP)
    String nip;

    @Pattern(regexp = "\\d{11}", message = "PESEL musi składać się z 11 cyfr")
    @JsonProperty(PESEL)
    String pesel;

    @JsonProperty(IS_BUSINESS_CLIENT)
    boolean isBusinessClient;

    @Builder(toBuilder = true)
    public ClientRegisterRequestDto(@JsonProperty(UUID) String uuid, @JsonProperty(FIRST_NAME) String firstName, @JsonProperty(LAST_NAME) String lastName, @JsonProperty(EMAIL) String email, @JsonProperty(PASSWORD) String password,
                                    @JsonProperty(ADDRESS) String address, @JsonProperty(BALANCE) double balance, @JsonProperty(PHONE_NUMBER) String phoneNumber,
                                    @JsonProperty(NIP) String nip, @JsonProperty(PESEL) String pesel, @JsonProperty(IS_BUSINESS_CLIENT) boolean isBusinessClient) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.balance = balance;
        this.phoneNumber = phoneNumber;
        this.nip = nip;
        this.pesel = pesel;
        this.isBusinessClient = isBusinessClient;
    }
}
