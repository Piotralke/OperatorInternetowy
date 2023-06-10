package pl.psk.upc.web.offer;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.infrastructure.dto.SaveProductWithOfferRequestDto;
import pl.psk.upc.infrastructure.entity.OfferType;
import pl.psk.upc.web.product.ProductDto;

@Value
public class SaveOfferRequestDto {

    private final static String NAME = "name";
    private final static String DESCRIPTION = "description";
    private final static String PRICE = "price";
    private final static String SAVE_PRODUCT_WITH_OFFER_REQUEST_DTO = "SaveProductWithOfferRequestDto";
    private final static String WITH_DEVICE = "withDevice";
    private final static String OFFER_TYPE = "offerType";

    @JsonProperty(NAME)
    String name;

    @JsonProperty(DESCRIPTION)
    String description;

    @JsonProperty(PRICE)
    double price;

    @JsonProperty(SAVE_PRODUCT_WITH_OFFER_REQUEST_DTO)
    SaveProductWithOfferRequestDto saveProductWithOfferRequestDto;

    @JsonProperty(WITH_DEVICE)
    boolean withDevice;

    @JsonProperty(OFFER_TYPE)
    OfferType offerType;

    @Builder
    public SaveOfferRequestDto(@JsonProperty(NAME) String name, @JsonProperty(DESCRIPTION) String description, @JsonProperty(PRICE) double price,
                               @JsonProperty(SAVE_PRODUCT_WITH_OFFER_REQUEST_DTO) SaveProductWithOfferRequestDto saveProductWithOfferRequestDto, @JsonProperty(WITH_DEVICE) boolean withDevice,
                               @JsonProperty(OFFER_TYPE) OfferType offerType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.saveProductWithOfferRequestDto = saveProductWithOfferRequestDto;
        this.withDevice = withDevice;
        this.offerType = offerType;
    }
}
