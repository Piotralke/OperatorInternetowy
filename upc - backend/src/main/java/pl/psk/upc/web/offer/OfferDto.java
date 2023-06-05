package pl.psk.upc.web.offer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.infrastructure.entity.OfferType;
import pl.psk.upc.web.product.ProductDto;

@Value
public class OfferDto {

    private final static String UUID = "uuid";
    private final static String NAME = "name";
    private final static String DESCRIPTION = "description";
    private final static String PRICE = "price";
    private final static String PRODUCT_DTO = "productDto";
    private final static String WITH_DEVICE = "withDevice";
    private final static String OFFER_TYPE = "offerType";

    @JsonProperty(UUID)
    java.util.UUID uuid;

    @JsonProperty(NAME)
    String name;

    @JsonProperty(DESCRIPTION)
    String description;

    @JsonProperty(PRICE)
    double price;

    @JsonProperty(PRODUCT_DTO)
    ProductDto productDto;

    @JsonProperty(WITH_DEVICE)
    boolean withDevice;

    @JsonProperty(OFFER_TYPE)
    OfferType offerType;

    @Builder
    public OfferDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(NAME) String name, @JsonProperty(DESCRIPTION) String description, @JsonProperty(PRICE) double price, @JsonProperty(PRODUCT_DTO) ProductDto productDto,
                    @JsonProperty(WITH_DEVICE) boolean withDevice, @JsonProperty(OFFER_TYPE) OfferType offerType) {
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productDto = productDto;
        this.withDevice = withDevice;
        this.offerType = offerType;
    }
}
