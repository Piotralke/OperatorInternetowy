package pl.psk.upc.application.offer;

import pl.psk.upc.infrastructure.entity.OfferType;
import pl.psk.upc.web.offer.OfferDtoWrapper;

import java.util.UUID;

public interface OfferService {
    OfferDtoWrapper getOffersByType(OfferType offerType);
    OfferDtoWrapper getAllOffers();
    OfferDtoWrapper getOfferByUuid(UUID uuid);
}
