package pl.psk.upc.web.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Value;
import pl.psk.upc.infrastructure.enums.ContractForm;

@Value
public class EmployeeRegisterRequestDto {

    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "last_name";
    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";
    private final static String ADDRESS = "address";
    private final static String WORKPLACE = "workplace";
    private final static String SALARY = "salary";
    private final static String CONTRACT_FORM = "contractForm";
    private final static String PHONE_NUMBER = "phoneNumber";
    private final static String NIP = "nip";
    private final static String PESEL = "pesel";

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

    @Pattern(regexp = "\\d{9}", message = "Numer telefonu musi składać się z 9 cyfr")
    @JsonProperty(PHONE_NUMBER)
    String phoneNumber;

    @NotBlank
    @JsonProperty(WORKPLACE)
    String workplace;

    @Positive
    @JsonProperty(SALARY)
    double salary;

    @NotNull
    @JsonProperty(CONTRACT_FORM)
    ContractForm contractForm;

    @Pattern(regexp = "\\d{10}", message = "NIP musi składać się z 10 cyfr")
    @JsonProperty(NIP)
    String nip;

    @Pattern(regexp = "\\d{11}", message = "PESEL musi składać się z 11 cyfr")
    @JsonProperty(PESEL)
    String pesel;

    public EmployeeRegisterRequestDto(@JsonProperty(FIRST_NAME) String firstName, @JsonProperty(LAST_NAME) String lastName, @JsonProperty(EMAIL) String email, @JsonProperty(PASSWORD) String password,
                                      @JsonProperty(ADDRESS) String address, @JsonProperty(PHONE_NUMBER) String phoneNumber, @JsonProperty(WORKPLACE) String workplace,
                                      @JsonProperty(SALARY) double salary, @JsonProperty(CONTRACT_FORM) ContractForm contractForm, @JsonProperty(NIP) String nip, @JsonProperty(PESEL) String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.workplace = workplace;
        this.salary = salary;
        this.contractForm = contractForm;
        this.nip = nip;
        this.pesel = pesel;
    }
}
