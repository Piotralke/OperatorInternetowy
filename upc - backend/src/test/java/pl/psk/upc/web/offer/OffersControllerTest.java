package pl.psk.upc.web.offer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.psk.upc.infrastructure.enums.OfferType;
import pl.psk.upc.web.UpcTest;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OffersControllerTest extends UpcTest {

    @Autowired OffersController offersController;

    @Test @Transactional
    void getOfferTypes() {
        List<OfferType> offerTypes = Arrays.stream(OfferType.values())
                .toList();
        List<String> offerTypesFromApi = offersController.getOfferTypes();

        assertEquals(offerTypes.size(), offerTypesFromApi.size());
    }

    @Test @Transactional
    void getOffersByType() {
        OfferType offerType = OfferType.INTERNET_PLUS_TV;
        OfferDtoWrapper offersByType = offersController.getOffersByType(offerType);
        for (OfferDto offer : offersByType.getContent()) {
            assertEquals(offerType, offer.getOfferType());
        }
    }

    @Test @Transactional
    void getAllOffers() {
        OfferDtoWrapper allOffers = offersController.getAllOffers();

        assertEquals(9, allOffers.getContent().size());
    }

    @Test @Transactional
    void getOfferByUuid() {
        OfferDto offerByUuid = offersController.getOfferByUuid(UUID.fromString("4461593f-b666-47eb-912d-65f677194724"));

        assertTrue(StringUtils.isNotBlank(offerByUuid.getDescription()));
        assertNotNull(offerByUuid.getUuid());
        assertFalse(offerByUuid.isArchival());
        assertEquals("Internet 300Mb/s", offerByUuid.getName());
        assertEquals(50.0, offerByUuid.getPrice());
        assertEquals(OfferType.INTERNET, offerByUuid.getOfferType());
    }

    @Test @Transactional
    void saveOffer() {
    }

    @Test @Transactional
    void editOfferStatus() {
    }
}