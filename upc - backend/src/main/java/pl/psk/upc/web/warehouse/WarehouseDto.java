package pl.psk.upc.web.warehouse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.web.product.ProductDto;

@Value
public class WarehouseDto {

    private final static String UUID = "uuid";
    private final static String PRODUCT_NAME = "productName";
    private final static String QUANTITY = "quantity";
    private final static String PRODUCT_DTO = "productDto";

    @JsonProperty(UUID)
    java.util.UUID uuid;

    @JsonProperty(PRODUCT_NAME)
    String productName;

    @JsonProperty(QUANTITY)
    Integer quantity;

    @JsonProperty(PRODUCT_DTO)
    ProductDto productDto;

    @Builder
    public WarehouseDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(PRODUCT_NAME) String productName, @JsonProperty(QUANTITY) Integer quantity, @JsonProperty(PRODUCT_DTO) ProductDto productDto) {
        this.uuid = uuid;
        this.productName = productName;
        this.quantity = quantity;
        this.productDto = productDto;
    }
}
