package pl.psk.upc.application.offer;

import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.entity.OfferEntity;
import pl.psk.upc.infrastructure.entity.OfferType;
import pl.psk.upc.infrastructure.repository.OfferRepository;
import pl.psk.upc.web.offer.OfferDtoWrapper;

import java.util.List;
import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public OfferDtoWrapper getOffersByType(OfferType offerType) {
        List<OfferEntity> offers = offerRepository.findByOfferType(offerType);
        return OfferConverter.convertFrom(offers);
    }

    @Override
    public OfferDtoWrapper getAllOffers() {
        return OfferConverter.convertFrom(offerRepository.findAll());
    }

    @Override
    public OfferDtoWrapper getOfferByUuid(UUID uuid) {
        List<OfferEntity> offers = offerRepository.findAll();
        return OfferConverter.convertFrom(offers);
    }
}
