package pl.psk.upc.web.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.infrastructure.entity.OrderEntity;
import pl.psk.upc.infrastructure.entity.ServiceEntity;
import pl.psk.upc.infrastructure.entity.UserProblemEntity;
import pl.psk.upc.web.order.OrderDto;
import pl.psk.upc.web.service.ServiceDto;
import pl.psk.upc.web.userproblem.UserProblemDto;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Value
public class ClientDto {

    private final static String UUID = "uuid";
    private final static String FIRST_NAME = "firstName";
    private final static String LAST_NAME = "lastName";
    private final static String EMAIL = "email";
    private final static String ADDRESS = "address";
    private final static String BALANCE = "balance";
    private final static String PHONE_NUMBER = "phoneNumber";
    private final static String NIP = "nip";
    private final static String PESEL = "pesel";
    private final static String ORDERS = "orders";
    private final static String USER_PROBLEMS = "userProblems";
    private final static String SERVICES = "services";
    private final static String IS_BUSINESS_CLIENT = "isBusinessClient";

    @JsonProperty(UUID)
    UUID uuid;

    @JsonProperty(FIRST_NAME)
    String firstName;

    @JsonProperty(LAST_NAME)
    String lastName;

    @JsonProperty(EMAIL)
    String email;

    @JsonProperty(ADDRESS)
    String address;

    @JsonProperty(BALANCE)
    double balance;

    @JsonProperty(PHONE_NUMBER)
    String phoneNumber;

    @JsonProperty(NIP)
    String nip;

    @JsonProperty(PESEL)
    String pesel;

    @JsonProperty(IS_BUSINESS_CLIENT)
    boolean isBusinessClient;

    @JsonProperty(ORDERS)
    List<OrderDto> orders;

    @JsonProperty(USER_PROBLEMS)
    List<UserProblemDto> userProblems;

    @JsonProperty(SERVICES)
    List<ServiceDto> services;

    @Builder
    public ClientDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(FIRST_NAME) String firstName, @JsonProperty(LAST_NAME)String lastName, @JsonProperty(EMAIL) String email, @JsonProperty(ADDRESS) String address,
                     @JsonProperty(BALANCE) double balance, @JsonProperty(PHONE_NUMBER) String phoneNumber, @JsonProperty(NIP) String nip, @JsonProperty(PESEL) String pesel,
                     @JsonProperty(ORDERS) List<OrderDto> orders, @JsonProperty(USER_PROBLEMS) List<UserProblemDto> userProblems, @JsonProperty(SERVICES) List<ServiceDto> services, @JsonProperty(IS_BUSINESS_CLIENT) boolean isBusinessClient) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.balance = balance;
        this.phoneNumber = phoneNumber;
        this.nip = nip;
        this.pesel = pesel;
        this.orders = orders;
        this.userProblems = userProblems;
        this.services = services;
        this.isBusinessClient = isBusinessClient;
    }

    public List<OrderDto> getOrders() {
        return this.orders == null ? Collections.emptyList() : this.orders;
    }

    public List<UserProblemDto> getUserProblems() {
        return this.userProblems == null ? Collections.emptyList() : this.userProblems;
    }

    public List<ServiceDto> getServices() {
        return this.services == null ? Collections.emptyList() : this.services;
    }
}
