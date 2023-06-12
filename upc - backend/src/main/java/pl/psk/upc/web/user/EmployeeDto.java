package pl.psk.upc.web.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.infrastructure.enums.ContractForm;

@Value
public class EmployeeDto {

    private final static String UUID = "uuid";
    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "lastName";
    private final static String EMAIL = "email";
    private final static String ACCOUNT_STATUS = "accountStatus";
    private final static String ADDRESS = "address";
    private final static String PHONE_NUMBER = "phoneNumber";
    private final static String WORKPLACE = "workplace";
    private final static String SALARY = "salary";
    private final static String CONTRACT_FORM = "contractForm";
    private final static String NIP = "nip";
    private final static String PESEL = "pesel";

    @JsonProperty(UUID)
    java.util.UUID uuid;

    @JsonProperty(FIRST_NAME)
    String firstName;

    @JsonProperty(LAST_NAME)
    String lastName;

    @JsonProperty(EMAIL)
    String email;

    @JsonProperty(ACCOUNT_STATUS)
    String accountStatus;

    @JsonProperty(ADDRESS)
    String address;

    @JsonProperty(PHONE_NUMBER)
    String phoneNumber;

    @JsonProperty(WORKPLACE)
    String workplace;

    @JsonProperty(SALARY)
    double salary;

    @JsonProperty(CONTRACT_FORM)
    ContractForm contractForm;

    @JsonProperty(NIP)
    String nip;

    @JsonProperty(PESEL)
    String pesel;

    @Builder
    public EmployeeDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(FIRST_NAME) String firstName, @JsonProperty(LAST_NAME) String lastName, @JsonProperty(EMAIL) String email, @JsonProperty(ACCOUNT_STATUS) String accountStatus,
                       @JsonProperty(ADDRESS) String address, @JsonProperty(PHONE_NUMBER) String phoneNumber, @JsonProperty(WORKPLACE) String workplace, @JsonProperty(SALARY) double salary, @JsonProperty(CONTRACT_FORM) ContractForm contractForm,
                       @JsonProperty(NIP) String nip, @JsonProperty(PESEL) String pesel) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.accountStatus = accountStatus;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.workplace = workplace;
        this.salary = salary;
        this.contractForm = contractForm;
        this.nip = nip;
        this.pesel = pesel;
    }
}
