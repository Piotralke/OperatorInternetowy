package pl.psk.upc.web.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
public class ClientEditRequestDto {

    private final static String UUID = "uuid";
    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "lastName";
    private final static String ADDRESS = "address";
    private final static String BALANCE = "balance";
    private final static String ACCOUNT_STATUS = "accountStatus";
    private final static String PHONE_NUMBER = "phoneNumber";
    private final static String NIP = "nip";
    private final static String IS_BUSINESS_CLIENT = "isBusinessClient";

    @JsonProperty(UUID)
    java.util.UUID uuid;

    @JsonProperty(FIRST_NAME)
    String firstName;

    @JsonProperty(LAST_NAME)
    String lastName;

    @JsonProperty(ADDRESS)
    String address;

    @JsonProperty(BALANCE)
    double balance;

    @JsonProperty(ACCOUNT_STATUS)
    String accountStatus;

    @JsonProperty(PHONE_NUMBER)
    String phoneNumber;

    @JsonProperty(NIP)
    String nip;

    @JsonProperty(IS_BUSINESS_CLIENT)
    boolean isBusinessClient;

    @Builder
    public ClientEditRequestDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(FIRST_NAME) String firstName, @JsonProperty(LAST_NAME)String lastName, @JsonProperty(ADDRESS) String address,
                     @JsonProperty(BALANCE) double balance, @JsonProperty(ACCOUNT_STATUS) String accountStatus, @JsonProperty(PHONE_NUMBER) String phoneNumber, @JsonProperty(NIP) String nip,
                                @JsonProperty(IS_BUSINESS_CLIENT) boolean isBusinessClient) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.balance = balance;
        this.accountStatus = accountStatus;
        this.phoneNumber = phoneNumber;
        this.nip = nip;
        this.isBusinessClient = isBusinessClient;
    }
}
