package pl.psk.upc.application.offer;

import pl.psk.upc.infrastructure.entity.OfferType;
import pl.psk.upc.web.offer.OfferDto;
import pl.psk.upc.web.offer.OfferDtoWrapper;
import pl.psk.upc.web.offer.SaveOfferRequestDto;

import java.util.UUID;

public interface OfferService {
    OfferDtoWrapper getOffersByType(OfferType offerType);
    OfferDtoWrapper getAllOffers();
    OfferDto getOfferByUuid(UUID uuid);
    OfferDto saveOffer(SaveOfferRequestDto saveOfferRequestDto);
}
