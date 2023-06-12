package pl.psk.upc.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;
import pl.psk.upc.infrastructure.entity.ContractForm;

@Value
public class EmployeeRegisterRequestDto {

    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "last_name";
    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";
    private final static String ACCOUNT_STATUS = "accountStatus";
    private final static String ADDRESS = "address";
    private final static String WORKPLACE = "workplace";
    private final static String SALARY = "salary";
    private final static String CONTRACT_FORM = "contractForm";
    private final static String PHONE_NUMBER = "phoneNumber";
    private final static String NIP = "nip";
    private final static String PESEL = "pesel";

    @JsonProperty(FIRST_NAME)
    private String firstName;

    @JsonProperty(LAST_NAME)
    private String lastName;

    @JsonProperty(EMAIL)
    private String email;

    @JsonProperty(PASSWORD)
    private String password;

    @JsonProperty(ACCOUNT_STATUS)
    private String accountStatus;

    @JsonProperty(ADDRESS)
    private String address;

    @JsonProperty(PHONE_NUMBER)
    private String phoneNumber;

    @JsonProperty(WORKPLACE)
    private String workplace;

    @JsonProperty(SALARY)
    private double salary;

    @JsonProperty(CONTRACT_FORM)
    private ContractForm contractForm;

    @JsonProperty(NIP)
    private String nip;

    @JsonProperty(PESEL)
    private String pesel;

    public EmployeeRegisterRequestDto(@JsonProperty(FIRST_NAME) String firstName, @JsonProperty(LAST_NAME) String lastName, @JsonProperty(EMAIL) String email, @JsonProperty(PASSWORD) String password,
                                      @JsonProperty(ACCOUNT_STATUS) String accountStatus, @JsonProperty(ADDRESS) String address, @JsonProperty(PHONE_NUMBER) String phoneNumber, @JsonProperty(WORKPLACE) String workplace,
                                      @JsonProperty(SALARY) double salary, @JsonProperty(CONTRACT_FORM) ContractForm contractForm, @JsonProperty(NIP) String nip, @JsonProperty(PESEL) String pesel) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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
