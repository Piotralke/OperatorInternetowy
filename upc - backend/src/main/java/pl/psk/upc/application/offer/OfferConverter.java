package pl.psk.upc.application.offer;

import pl.psk.upc.application.product.ProductConverter;
import pl.psk.upc.infrastructure.entity.OfferEntity;
import pl.psk.upc.web.offer.OfferDto;
import pl.psk.upc.web.offer.OfferDtoWrapper;

import java.util.List;

public class OfferConverter {

    public static OfferDto convertFrom(OfferEntity offer) {
        return OfferDto.builder()
                .uuid(offer.getUuid())
                .name(offer.getName())
                .description(offer.getDescription())
                .price(offer.getPrice())
                .productDto(ProductConverter.convertFrom(offer.getProductEntity()))
                .withDevice(offer.isWithDevice())
                .offerType(offer.getOfferType())
                .isArchival(offer.isArchival())
                .build();
    }

    public static OfferDtoWrapper convertFrom(List<OfferEntity> offers) {
        List<OfferDto> convertedOffers = offers.stream()
                .map(OfferConverter::convertFrom)
                .toList();

        return OfferDtoWrapper.builder()
                .content(convertedOffers)
                .build();
    }

}
