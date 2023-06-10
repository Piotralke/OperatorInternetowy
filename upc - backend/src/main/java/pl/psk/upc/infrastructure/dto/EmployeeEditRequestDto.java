package pl.psk.upc.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class EmployeeEditRequestDto {

    private final static String UUID = "uuid";
    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "last_name";
    private final static String PASSWORD = "password";
    private final static String ACCOUNT_STATUS = "accountStatus";
    private final static String ADDRESS = "address";
    private final static String WORKPLACE = "workplace";
    private final static String SALARY = "salary";
    private final static String CONTRACT_FORM = "contractForm";
    private final static String PHONE_NUMBER = "phoneNumber";
    private final static String NIP = "nip";

    @JsonProperty(UUID)
    private java.util.UUID uuid;

    @JsonProperty(FIRST_NAME)
    private String firstName;

    @JsonProperty(LAST_NAME)
    private String lastName;

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
    private String contractForm;

    @JsonProperty(NIP)
    private String nip;

    public EmployeeEditRequestDto(@JsonProperty(UUID) java.util.UUID uuid,@JsonProperty(FIRST_NAME) String firstName, @JsonProperty(LAST_NAME) String lastName, @JsonProperty(PASSWORD) String password, @JsonProperty(ACCOUNT_STATUS) String accountStatus,
                                  @JsonProperty(ADDRESS) String address, @JsonProperty(PHONE_NUMBER) String phoneNumber, @JsonProperty(WORKPLACE) String workplace, @JsonProperty(SALARY) double salary,
                                  @JsonProperty(CONTRACT_FORM) String contractForm, @JsonProperty(NIP) String nip) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.accountStatus = accountStatus;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.workplace = workplace;
        this.salary = salary;
        this.contractForm = contractForm;
        this.nip = nip;
    }
}
