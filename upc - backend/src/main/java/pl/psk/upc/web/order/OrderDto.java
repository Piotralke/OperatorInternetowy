package pl.psk.upc.web.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import pl.psk.upc.infrastructure.entity.OrderStatus;
import pl.psk.upc.infrastructure.entity.PaymentStatus;
import pl.psk.upc.web.product.ProductDto;
import pl.psk.upc.web.service.ServiceDto;
import pl.psk.upc.web.user.ClientDto;
import pl.psk.upc.web.user.EmployeeDto;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class OrderDto {

    private final static String UUID = "uuid";
    private final static String ORDER_DATE = "orderDate";
    private final static String ORDER_STATUS = "orderStatus";
    private final static String PAYMENT_STATUS = "paymentStatus";
    private final static String CLIENT = "clientDto";
    private final static String EMPLOYEE = "employeeDto";
    private final static String PRODUCTS = "productDtos";
    private final static String SERVICE = "service";

    @JsonProperty(UUID)
    java.util.UUID uuid;

    @JsonProperty(ORDER_DATE)
    ZonedDateTime orderDate;

    @JsonProperty(ORDER_STATUS)
    OrderStatus orderStatus;

    @JsonProperty(PAYMENT_STATUS)
    PaymentStatus paymentStatus;

    @JsonProperty(CLIENT)
    ClientDto clientDto;

    @JsonProperty(EMPLOYEE)
    EmployeeDto employeeDto;

    @JsonProperty(PRODUCTS)
    List<ProductDto> productDtos;

    @JsonProperty(SERVICE)
    ServiceDto service;

    @Builder
    public OrderDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(ORDER_DATE) ZonedDateTime orderDate,
                    @JsonProperty(ORDER_STATUS) OrderStatus orderStatus, @JsonProperty(PAYMENT_STATUS) PaymentStatus paymentStatus, @JsonProperty(CLIENT) ClientDto clientDto,
                    @JsonProperty(EMPLOYEE) EmployeeDto employeeDto, @JsonProperty(PRODUCTS) List<ProductDto> productDtos, @JsonProperty(SERVICE) ServiceDto service) {
        this.uuid = uuid;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
        this.clientDto = clientDto;
        this.employeeDto = employeeDto;
        this.productDtos = productDtos;
        this.service = service;
    }
}
