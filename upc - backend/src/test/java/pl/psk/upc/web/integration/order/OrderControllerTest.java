package pl.psk.upc.web.integration.order;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.psk.upc.application.client.ClientService;
import pl.psk.upc.exception.GenericException;
import pl.psk.upc.infrastructure.enums.ContractForm;
import pl.psk.upc.infrastructure.enums.OfferType;
import pl.psk.upc.infrastructure.enums.OrderStatus;
import pl.psk.upc.infrastructure.enums.ProductType;
import pl.psk.upc.web.UpcTest;
import pl.psk.upc.web.contract.ContractDto;
import pl.psk.upc.web.offer.OfferDto;
import pl.psk.upc.web.order.OrderController;
import pl.psk.upc.web.order.OrderDto;
import pl.psk.upc.web.order.OrderDtoWrapper;
import pl.psk.upc.web.product.ProductDto;
import pl.psk.upc.web.service.ServiceDto;
import pl.psk.upc.web.user.ClientDto;
import pl.psk.upc.web.user.EmployeeDto;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrderControllerTest extends UpcTest {

    @Autowired
    OrderController orderController;

    @Autowired
    ClientService clientService;

    @Test
    @Transactional
    void saveClientOrderWithoutProducts() {
        UUID uuid = orderController.saveOrder(clientOrderInputDtoWithoutProducts);
        OrderDto order = orderController.getOrder(uuid);

        ClientDto clientByEmail = clientService.findByEmail(clientOrderInputDtoWithoutProducts.getClientEmail());

        assertEquals(1, clientByEmail.getOrders().size());
        assertEquals(1, clientByEmail.getServices().size());

        assertNotNull(order.getUuid());
        assertNotNull(order.getOrderDate());
        assertEquals(OrderStatus.PRZYJETE, order.getOrderStatus());
        assertEquals(140.0, order.getAmount());
        assertEquals(0, order.getProductDtos().size());
        testEmployeeValues(order.getEmployeeDto(), false);

        testService(order.getService());
    }

    @Test
    @Transactional
    void saveClientOrderWithProducts() {
        UUID uuid = orderController.saveOrder(clientOrderInputDtoWithProducts);

        ClientDto clientByEmail = clientService.findByEmail(clientOrderInputDtoWithProducts.getClientEmail());

        assertEquals(1, clientByEmail.getOrders().size());
        assertEquals(1, clientByEmail.getServices().size());

        testOrderWithProduct(uuid, false);
    }

    @Test
    @Transactional
    void save2ClientOrderWithProducts() {
        UUID uuid = orderController.saveOrder(clientOrderInputDtoWithProducts);
        UUID uuid2 = orderController.saveOrder(clientOrderInputDtoWithProducts);

        ClientDto clientByEmail = clientService.findByEmail(clientOrderInputDtoWithProducts.getClientEmail());

        assertEquals(2, clientByEmail.getOrders().size());
        assertEquals(2, clientByEmail.getServices().size());

        testOrderWithProduct(uuid, false);
        testOrderWithProduct(uuid2, false);
    }

    @Test
    @Transactional
    void saveEmployeeOrderWithoutProducts() {
        UUID uuid = orderController.saveOrder(employeeOrderInputDto);

        testOrderWithProduct(uuid, true);
    }

    @Test
    @Transactional
    void getOrder() {
        UUID uuid = orderController.saveOrder(clientOrderInputDtoWithProducts);
        testOrderWithProduct(uuid, false);
    }

    @Test
    @Transactional
    void throwExceptionIfNotPayedAndSetStatusToEnded() {
        UUID uuid = orderController.saveOrder(clientOrderInputDtoWithProducts);
        testOrderWithProduct(uuid, false);

        assertThrows(GenericException.class, () -> orderController.editOrderStatus(uuid, OrderStatus.ZAKONCZONE));
    }

    @Test
    @Transactional
    void getOrdersByEmployeeEmail() {
        OrderDtoWrapper ordersByEmployeeEmailBeforeSave = orderController.getOrdersByEmployeeEmail(employeeOrderInputDto.getEmployeeEmail());
        assertEquals(0, ordersByEmployeeEmailBeforeSave.getContent().size());

        orderController.saveOrder(employeeOrderInputDto);

        OrderDtoWrapper ordersByEmployeeEmailAfterSave = orderController.getOrdersByEmployeeEmail(employeeOrderInputDto.getEmployeeEmail());
        assertEquals(1, ordersByEmployeeEmailAfterSave.getContent().size());
    }

    @Test
    @Transactional
    void getOrdersByEmployeeUuid() {
        OrderDtoWrapper ordersByEmployeeEmailBeforeSave = orderController.getOrdersByEmployeeUuid(EMPLOYEE_UUID);
        assertEquals(0, ordersByEmployeeEmailBeforeSave.getContent().size());

        orderController.saveOrder(employeeOrderInputDto);

        OrderDtoWrapper ordersByEmployeeEmailAfterSave = orderController.getOrdersByEmployeeUuid(EMPLOYEE_UUID);
        assertEquals(1, ordersByEmployeeEmailAfterSave.getContent().size());
    }

    @Test
    @Transactional
    void getOrdersByClientEmail() {
        OrderDtoWrapper ordersByClientEmailBeforeSave = orderController.getOrdersByClientEmail(clientOrderInputDtoWithProducts.getClientEmail());
        assertEquals(0, ordersByClientEmailBeforeSave.getContent().size());

        orderController.saveOrder(clientOrderInputDtoWithProducts);
        orderController.saveOrder(clientOrderInputDtoWithProducts);

        OrderDtoWrapper ordersByClientEmailAfterSave = orderController.getOrdersByClientEmail(clientOrderInputDtoWithProducts.getClientEmail());
        assertEquals(2, ordersByClientEmailAfterSave.getContent().size());
    }

    @Test
    @Transactional
    void getOrdersByClientUuid() {
        OrderDtoWrapper ordersByClientEmailBeforeSave = orderController.getOrdersByClientUuid(clientAccount.getUuid());
        assertEquals(0, ordersByClientEmailBeforeSave.getContent().size());

        orderController.saveOrder(clientOrderInputDtoWithProducts);
        orderController.saveOrder(clientOrderInputDtoWithProducts);

        ClientDto clientByEmail = clientService.findByEmail(clientOrderInputDtoWithProducts.getClientEmail());
        List<OrderDto> orders = clientByEmail.getOrders();

        OrderDtoWrapper ordersByClientEmailAfterSave = orderController.getOrdersByClientUuid(clientAccount.getUuid());
        assertEquals(2, ordersByClientEmailAfterSave.getContent().size());
    }

    @Test
    @Transactional
    void editOrderStatus() {
        UUID uuid = orderController.saveOrder(clientOrderInputDtoWithProducts);
        testOrderWithProduct(uuid, false);

        OrderDto order = orderController.getOrder(uuid);
        assertEquals(OrderStatus.PRZYJETE, order.getOrderStatus());
        orderController.editOrderStatus(uuid, OrderStatus.PRZYJETE);

        OrderDto orderAfterUpdate = orderController.getOrder(uuid);
        assertEquals(OrderStatus.W_TRAKCIE_PRZYGOTOWANIA, orderAfterUpdate.getOrderStatus());
    }

    private void testOrderWithProduct(UUID uuid, boolean withEmployee) {
        OrderDto order = orderController.getOrder(uuid);
        assertNotNull(order.getUuid());
        assertNotNull(order.getOrderDate());
        assertEquals(OrderStatus.PRZYJETE, order.getOrderStatus());
        assertEquals(2288.0, order.getAmount());
        assertEquals(2, order.getProductDtos().size());
        testEmployeeValues(order.getEmployeeDto(), withEmployee);
        testProducts(order.getProductDtos());
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

    private void testEmployeeValues(EmployeeDto employee, boolean withEmployee) {
        if (withEmployee) {
            assertNotNull(employee.getUuid());
            assertEquals("Alejandra", employee.getFirstName());
            assertEquals("Langworth", employee.getLastName());
            assertEquals(EMPLOYEE_EMAIL, employee.getEmail());
            assertNull(employee.getAddress());
            assertEquals("661412255", employee.getPhoneNumber());
            assertEquals("Kielce", employee.getWorkplace());
            assertEquals(ContractForm.B2B, employee.getContractForm());
            assertNull(employee.getNip());
            assertNull(employee.getPesel());
            assertEquals(12000.0, employee.getSalary());
        } else {
            assertNull(employee.getUuid());
            assertNull(employee.getFirstName());
            assertNull(employee.getLastName());
            assertNull(employee.getEmail());
            assertNull(employee.getAddress());
            assertNull(employee.getPhoneNumber());
            assertNull(employee.getWorkplace());
            assertNull(employee.getContractForm());
            assertNull(employee.getNip());
            assertNull(employee.getPesel());
            assertEquals(0.0, employee.getSalary());
        }

    }

    private void testProducts(List<ProductDto> products) {
        ProductDto firstProduct = products.get(0);
        ProductDto secondProduct = products.get(1);

        assertNotNull(firstProduct.getUuid());
        assertEquals("Xiaomi POCO X5 Pro 5G 8/256GB", firstProduct.getName());
        assertEquals(1799.0, firstProduct.getPrice());
        assertTrue(StringUtils.isNotBlank(firstProduct.getDescription()));
        assertEquals(ProductType.MOBILE, firstProduct.getProductType());

        assertNotNull(secondProduct.getUuid());
        assertEquals("Router TP-LINK Archer AX53", secondProduct.getName());
        assertEquals(349.0, secondProduct.getPrice());
        assertTrue(StringUtils.isNotBlank(secondProduct.getDescription()));
        assertEquals(ProductType.INTERNET, secondProduct.getProductType());

    }
}