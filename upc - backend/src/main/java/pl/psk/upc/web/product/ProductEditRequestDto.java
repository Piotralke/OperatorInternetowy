package pl.psk.upc.web.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Value;

@Value
public class ProductEditRequestDto {

    private final static String UUID = "uuid";
    private final static String PRICE = "price";
    private final static String DESCRIPTION = "description";

    @NotNull
    @JsonProperty(UUID)
    java.util.UUID uuid;

    @NotNull
    @Positive
    @JsonProperty(PRICE)
    Double price;

    @NotBlank
    @JsonProperty(DESCRIPTION)
    String description;

    public ProductEditRequestDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(PRICE) Double price, @JsonProperty(DESCRIPTION) String description) {
        this.uuid = uuid;
        this.price = price;
        this.description = description;
    }

}
