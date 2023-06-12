package pl.psk.upc.web.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.infrastructure.enums.PaymentStatus;
import pl.psk.upc.web.product.ProductDto;
import pl.psk.upc.web.service.ServiceDto;

import java.time.ZonedDateTime;
import java.util.List;

@Value
public class InvoiceDto {

    private final static String UUID = "uuid";
    private final static String NAME = "name";
    private final static String CREATION_DATE = "creationDate";
    private final static String PAYMENT_DATE = "paymentDate";
    private final static String AMOUNT = "amount";
    private final static String PAYMENT_STATUS = "paymentStatus";
    private final static String PRODUCTS = "productDtos";
    private final static String SERVICE = "service";
    private final static String CLIENT_FIRST_NAME = "clientFirstName";
    private final static String CLIENT_LAST_NAME = "clientLastName";
    private final static String CLIENT_ADDRESS = "clientAddress";
    private final static String CLIENT_PESEL = "clientPesel";

    @JsonProperty(UUID)
    java.util.UUID uuid;

    @JsonProperty(NAME)
    String name;

    @JsonProperty(CREATION_DATE)
    ZonedDateTime creationDate;

    @JsonProperty(PAYMENT_DATE)
    ZonedDateTime paymentDate;

    @JsonProperty(AMOUNT)
    double amount;

    @JsonProperty(PAYMENT_STATUS)
    PaymentStatus paymentStatus;

    @JsonProperty(PRODUCTS)
    List<ProductDto> productDtos;

    @JsonProperty(SERVICE)
    ServiceDto service;

    @JsonProperty(CLIENT_FIRST_NAME)
    String clientFirstName;

    @JsonProperty(CLIENT_LAST_NAME)
    String clientLastName;

    @JsonProperty(CLIENT_ADDRESS)
    String clientAddress;

    @JsonProperty(CLIENT_PESEL)
    String clientPesel;


    @Builder
    public InvoiceDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(NAME) String name, @JsonProperty(CREATION_DATE) ZonedDateTime creationDate, @JsonProperty(AMOUNT) double amount,
                      @JsonProperty(PAYMENT_STATUS) PaymentStatus paymentStatus, @JsonProperty(PRODUCTS) List<ProductDto> productDtos,@JsonProperty(PAYMENT_DATE) ZonedDateTime paymentDate,
                      @JsonProperty(SERVICE) ServiceDto service, @JsonProperty(CLIENT_FIRST_NAME) String clientFirstName, @JsonProperty(CLIENT_LAST_NAME) String clientLastName,
                      @JsonProperty(CLIENT_ADDRESS) String clientAddress, @JsonProperty(CLIENT_PESEL) String clientPesel) {
        this.uuid = uuid;
        this.name = name;
        this.creationDate = creationDate;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.productDtos = productDtos;
        this.paymentDate = paymentDate;
        this.service = service;
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.clientAddress = clientAddress;
        this.clientPesel = clientPesel;
    }
}
