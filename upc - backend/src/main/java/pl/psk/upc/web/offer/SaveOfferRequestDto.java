package pl.psk.upc.web.offer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.infrastructure.enums.OfferType;

@Value
public class SaveOfferRequestDto {

    private final static String NAME = "name";
    private final static String DESCRIPTION = "description";
    private final static String PRICE = "price";
    private final static String SAVE_PRODUCT_WITH_OFFER_REQUEST_DTO = "SaveProductWithOfferRequestDto";
    private final static String WITH_DEVICE = "withDevice";
    private final static String OFFER_TYPE = "offerType";

    @NotBlank
    @JsonProperty(NAME)
    String name;

    @NotBlank
    @JsonProperty(DESCRIPTION)
    String description;

    @Positive
    @JsonProperty(PRICE)
    double price;

    @NotNull
    @JsonProperty(SAVE_PRODUCT_WITH_OFFER_REQUEST_DTO)
    SaveProductWithOfferRequestDto saveProductWithOfferRequestDto;

    @JsonProperty(WITH_DEVICE)
    boolean withDevice;

    @NotNull
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
