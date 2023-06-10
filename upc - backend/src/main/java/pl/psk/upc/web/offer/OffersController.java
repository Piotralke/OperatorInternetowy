package pl.psk.upc.web.offer;

import org.springframework.web.bind.annotation.*;
import pl.psk.upc.application.offer.OfferService;
import pl.psk.upc.infrastructure.entity.OfferType;
import pl.psk.upc.web.UpcRestPaths;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class OffersController {

    private final OfferService offerService;

    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping(UpcRestPaths.GET_OFFER_TYPES)
    public List<String> getOfferTypes() {
        return Arrays.stream(OfferType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @GetMapping(UpcRestPaths.GET_OFFERS_BY_TYPE)
    public OfferDtoWrapper getOffersByType(@RequestParam OfferType offerType) {
        return offerService.getOffersByType(offerType);
    }

    @GetMapping(UpcRestPaths.GET_ALL_OFFERS)
    public OfferDtoWrapper getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping(UpcRestPaths.GET_OFFER_BY_UUID)
    public OfferDto getOfferByUuid(@PathVariable(value = "uuid") UUID uuid) {
        return offerService.getOfferByUuid(uuid);
    }

    @GetMapping(UpcRestPaths.SAVE_OFFER)
    public OfferDto saveOffer(@RequestBody SaveOfferRequestDto saveOfferRequestDto) {
        return offerService.saveOffer(saveOfferRequestDto);
    }

}
