package pl.psk.upc.web.offer;

import org.springframework.web.bind.annotation.*;
import pl.psk.upc.infrastructure.entity.OfferEntity;
import pl.psk.upc.infrastructure.entity.OfferType;
import pl.psk.upc.infrastructure.repository.OfferRepository;
import pl.psk.upc.web.UpcRestPaths;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class OffersController {

    private final OfferRepository offerRepository;

    public OffersController(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @GetMapping(UpcRestPaths.GET_OFFER_TYPES)
    public List<String> getOfferTypes() {
        return Arrays.stream(OfferType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping(UpcRestPaths.GET_OFFERS_BY_TYPE)
    public List<OfferEntity> getOffersByType(@RequestParam OfferType offerType) {
        return offerRepository.findByOfferType(offerType);
    }

    @GetMapping(UpcRestPaths.GET_ALL_OFFERS)
    public List<OfferEntity> getAllOffers() {
        return offerRepository.findAll();
    }

    @GetMapping(UpcRestPaths.GET_OFFER_BY_UUID)
    public OfferEntity getOfferByUuid(@PathVariable(value = "uuid") UUID uuid) {
        return offerRepository.findByUuid(uuid);
    }

}
