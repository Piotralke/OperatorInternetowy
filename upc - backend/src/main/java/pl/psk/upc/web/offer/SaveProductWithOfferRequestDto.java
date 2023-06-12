package pl.psk.upc.web.offer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.infrastructure.enums.ProductType;

@Value
public class SaveProductWithOfferRequestDto {

    private final static String UUID = "uuid";
    private final static String NAME = "name";
    private final static String PRICE = "price";
    private final static String DESCRIPTION = "description";
    private final static String PRODUCT_TYPE = "productType";

    @JsonProperty(UUID)
    java.util.UUID uuid;

    @JsonProperty(NAME)
    String name;

    @JsonProperty(PRICE)
    Double price;

    @JsonProperty(DESCRIPTION)
    String description;

    @JsonProperty(PRODUCT_TYPE)
    ProductType productType;

    @Builder
    public SaveProductWithOfferRequestDto(@JsonProperty(UUID) java.util.UUID uuid,@JsonProperty(NAME) String name, @JsonProperty(PRICE) Double price, @JsonProperty(DESCRIPTION) String description, @JsonProperty(PRODUCT_TYPE) ProductType productType) {
        this.uuid = uuid;
        this.name = name;
        this.price = price;
        this.description = description;
        this.productType = productType;
    }

}
