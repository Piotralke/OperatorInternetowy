package pl.psk.upc.web.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class ClientRegisterRequestDto {

    private final static String UUID = "uuid";
    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "last_name";
    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";
    private final static String ADDRESS = "address";
    private final static String BALANCE = "balance";
    private final static String ACCOUNT_STATUS = "accountStatus";
    private final static String PHONE_NUMBER = "phoneNumber";
    private final static String NIP = "nip";
    private final static String PESEL = "pesel";
    private final static String IS_BUSINESS_CLIENT = "isBusinessClient";

    @JsonProperty(UUID)
    private String uuid;

    @JsonProperty(FIRST_NAME)
    private String firstName;

    @JsonProperty(LAST_NAME)
    private String lastName;

    @JsonProperty(EMAIL)
    private String email;

    @JsonProperty(PASSWORD)
    private String password;

    @JsonProperty(ADDRESS)
    private String address;

    @JsonProperty(BALANCE)
    private String balance;

    @JsonProperty(ACCOUNT_STATUS)
    private String accountStatus;

    @JsonProperty(PHONE_NUMBER)
    private String phoneNumber;

    @JsonProperty(NIP)
    private String nip;

    @JsonProperty(PESEL)
    private String pesel;

    @JsonProperty(IS_BUSINESS_CLIENT)
    private boolean isBusinessClient;

    public ClientRegisterRequestDto(@JsonProperty(UUID) String uuid, @JsonProperty(FIRST_NAME) String firstName, @JsonProperty(LAST_NAME) String lastName, @JsonProperty(EMAIL) String email, @JsonProperty(PASSWORD) String password,
                                    @JsonProperty(ADDRESS) String address, @JsonProperty(BALANCE) String balance, @JsonProperty(ACCOUNT_STATUS) String accountStatus, @JsonProperty(PHONE_NUMBER) String phoneNumber,
                                    @JsonProperty(NIP) String nip, @JsonProperty(PESEL) String pesel, @JsonProperty(IS_BUSINESS_CLIENT) boolean isBusinessClient) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.balance = balance;
        this.accountStatus = accountStatus;
        this.phoneNumber = phoneNumber;
        this.nip = nip;
        this.pesel = pesel;
        this.isBusinessClient = isBusinessClient;
    }
}
