package pl.psk.upc.web.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.infrastructure.entity.ProductType;

@Value
public class SaveProductRequestDto {

    private final static String NAME = "name";
    private final static String PRICE = "price";
    private final static String DESCRIPTION = "description";
    private final static String PRODUCT_TYPE = "productType";

    @JsonProperty(NAME)
    String name;

    @JsonProperty(PRICE)
    Double price;

    @JsonProperty(DESCRIPTION)
    String description;

    @JsonProperty(PRODUCT_TYPE)
    ProductType productType;

    @Builder
    public SaveProductRequestDto(@JsonProperty(NAME) String name, @JsonProperty(PRICE) Double price, @JsonProperty(DESCRIPTION) String description, @JsonProperty(PRODUCT_TYPE) ProductType productType) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.productType = productType;
    }

}
