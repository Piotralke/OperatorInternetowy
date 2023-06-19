package pl.psk.upc.web.integration.contract;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.psk.upc.application.contract.ContractService;
import pl.psk.upc.infrastructure.entity.ContractEntity;
import pl.psk.upc.infrastructure.enums.ContractLengthEnum;
import pl.psk.upc.infrastructure.enums.OfferType;
import pl.psk.upc.infrastructure.repository.ContractRepository;
import pl.psk.upc.web.UpcTest;
import pl.psk.upc.web.contract.ContractController;
import pl.psk.upc.web.contract.ContractDto;
import pl.psk.upc.web.contract.ContractDtoWrapper;
import pl.psk.upc.web.offer.OfferDto;
import pl.psk.upc.web.order.OrderController;
import pl.psk.upc.web.order.OrderDto;
import pl.psk.upc.web.service.ServiceDto;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContractControllerTest extends UpcTest {

    @Autowired
    ContractController contractController;

    @Autowired
    OrderController orderController;

    @Test
    @Transactional
    void getContractLengths() {
        List<ContractLengthEnum> contractLengthsFromApi = contractController.getContractLengths();
        Assertions.assertEquals(ContractLengthEnum.values().length, contractLengthsFromApi.size());
    }

    @Test
    @Transactional
    void getContractByUuid() {
        UUID uuid = orderController.saveOrder(clientOrderInputDtoWithoutProducts);
        OrderDto order = orderController.getOrder(uuid);
        UUID contractUuid = order.getService().getContract().getUuid();

        ContractDto contractByUuid = contractController.getContractByUuid(contractUuid);
        testContract(contractByUuid);
    }

    @Test
    @Transactional
    void getContractByUserUuid() {
        UUID uuid = orderController.saveOrder(clientOrderInputDtoWithoutProducts);
        orderController.getOrder(uuid);

        ContractDtoWrapper contractByUserUuid = contractController.getContractByUserUuid(clientAccount.getUuid());
        assertEquals(1, contractByUserUuid.getContent().size());
        for (ContractDto c : contractByUserUuid.getContent()) {
            testContract(c);
        }
    }

    @Test
    @Transactional
    void getContractByServiceUuid() {
        UUID uuid = orderController.saveOrder(clientOrderInputDtoWithoutProducts);
        ServiceDto service = orderController.getOrder(uuid)
                .getService();

        ContractDto contractByServiceUuid = contractController.getContractByServiceUuid(service.getUuid());
        testContract(contractByServiceUuid);
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