package pl.psk.upc.web.integration.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.psk.upc.infrastructure.enums.OfferType;
import pl.psk.upc.web.UpcTest;
import pl.psk.upc.web.contract.ContractDto;
import pl.psk.upc.web.offer.OfferDto;
import pl.psk.upc.web.order.OrderController;
import pl.psk.upc.web.order.OrderDto;
import pl.psk.upc.web.service.ServiceController;
import pl.psk.upc.web.service.ServiceDto;
import pl.psk.upc.web.service.ServiceDtoWrapper;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceControllerTest extends UpcTest {

    @Autowired
    ServiceController serviceController;

    @Autowired
    OrderController orderController;

    @Test @Transactional
    void getServices() {
        ServiceDtoWrapper servicesBeforeSave = serviceController.getServices();
        Assertions.assertEquals(0, servicesBeforeSave.getContent().size());

        orderController.saveOrder(clientOrderInputDtoWithoutProducts);
        orderController.saveOrder(clientOrderInputDtoWithoutProducts);

        ServiceDtoWrapper servicesAfterSave = serviceController.getServices();
        Assertions.assertEquals(2, servicesAfterSave.getContent().size());

        for (ServiceDto serviceDto : servicesAfterSave.getContent()) {
            testService(serviceDto);
        }
    }

    @Test @Transactional
    void getService() {
        UUID uuid = orderController.saveOrder(clientOrderInputDtoWithoutProducts);
        OrderDto order = orderController.getOrder(uuid);
        UUID serviceUuid = order.getService().getUuid();

        ServiceDto service = serviceController.getService(serviceUuid);

        testService(service);
    }

    private void testService(ServiceDto service) {
        assertNotNull(service.getUuid());
        assertEquals("Internet 1Gb/s + Telewizja 179 kanałów", service.getName());
        assertEquals(OfferType.INTERNET_PLUS_TV, service.getOfferType());
        testContract(service.getContract());
    }

    private void testContract(ContractDto contract) {
        assertNotNull(contract.getUuid());
        assertNotNull(contract.getStartDate());
        assertEquals(contract.getStartDate().plusYears(1L), contract.getEndDate());
        assertEquals(140.0, contract.getAmount());
        assertEquals(0, contract.getPayments().size());
        testOffer(contract.getOffer());
    }

    private void testOffer(OfferDto offer) {
        assertNotNull(offer.getUuid());
        assertEquals("Internet 1Gb/s + Telewizja 179 kanałów", offer.getName());
        assertTrue(StringUtils.isNotBlank(offer.getDescription()));
        assertEquals(140.0, offer.getPrice());
        assertTrue(offer.isWithDevice());
        assertFalse(offer.isArchival());
        assertEquals(OfferType.INTERNET_PLUS_TV, offer.getOfferType());
    }

}