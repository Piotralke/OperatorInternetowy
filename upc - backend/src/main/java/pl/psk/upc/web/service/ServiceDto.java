package pl.psk.upc.web.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.infrastructure.entity.OfferType;
import pl.psk.upc.infrastructure.entity.ProductType;
import pl.psk.upc.web.product.ProductDto;

import java.util.UUID;

@Value
public class ServiceDto {

    private final static String UUID = "uuid";
    private final static String NAME = "name";
    private final static String DESCRIPTION = "description";
    private final static String PRICE = "price";
    private final static String PRODUCT_TYPE = "productType";

    @JsonProperty(UUID)
    java.util.UUID uuid;

    @JsonProperty(NAME)
    String name;

    @JsonProperty(DESCRIPTION)
    String description;

    @JsonProperty(PRICE)
    double price;

    @JsonProperty(PRODUCT_TYPE)
    ProductType productType;

    @Builder
    public ServiceDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(NAME) String name, @JsonProperty(DESCRIPTION) String description, @JsonProperty(PRICE) double price, @JsonProperty(PRODUCT_TYPE) ProductType productType) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productType = productType;
    }
}
