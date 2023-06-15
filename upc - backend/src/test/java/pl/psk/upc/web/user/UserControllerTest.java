package pl.psk.upc.web.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.psk.upc.application.client.ClientService;
import pl.psk.upc.infrastructure.enums.ContractForm;
import pl.psk.upc.web.UpcTest;
import pl.psk.upc.web.order.OrderController;
import pl.psk.upc.web.order.OrderDto;
import pl.psk.upc.web.order.OrderDtoWrapper;
import pl.psk.upc.web.service.ServiceDto;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest extends UpcTest {

    @Autowired
    UserController userController;

    @Autowired
    OrderController orderController;

    @Autowired
    ClientService clientService;

    @Test
    @Transactional
    void getUserByEmail() {
        ClientDto userByEmail = userController.getUserByEmail(clientAccount.getEmail());

        testClient(userByEmail);
    }

    @Test
    @Transactional
    void getUserByUuid() {
        ClientDto user = userController.getUserByUuid(clientAccount.getUuid());

        testClient(user);
    }

    @Test
    @Transactional
    void getAllUsers() {
        ClientDtoWrapper allUsers = userController.getAllUsers();

        assertEquals(10, allUsers.getContent().size());
    }

    @Test
    @Transactional
    void getEmployeeByUuid() {
        UUID uuid = UUID.fromString("53bd49ca-9599-4c9a-b179-9c6d597845b5");
        EmployeeDto employee = userController.getEmployeeByUuid(uuid);

        testEmployee(employee);
    }

    @Test
    @Transactional
    void getContractForms() {
        List<ContractForm> contractFormsFromApi = userController.getContractForms();
        assertEquals(ContractForm.values().length, contractFormsFromApi.size());
    }

    @Test
    @Transactional
    void getEmployeeByEmail() {
        EmployeeDto employee = userController.getEmployeeByEmail("jarod.howell@yahoo.com");

        testEmployee(employee);
    }

    @Test
    @Transactional
    void getAllEmployees() {
        EmployeeWrapper allEmployees = userController.getAllEmployees();

        assertEquals(2, allEmployees.getContent().size());
    }

    @Test
    @Transactional
    void getUserServices() {
        List<ServiceDto> userServicesBeforeSave = userController.getUserServices(clientAccount.getEmail());

        assertEquals(0, userServicesBeforeSave.size());

        orderController.saveOrder(clientOrderInputDtoWithProducts);

        List<ServiceDto> userServicesAfterSave = userController.getUserServices(clientAccount.getEmail());

        assertEquals(1, userServicesAfterSave.size());

        orderController.saveOrder(clientOrderInputDtoWithProducts);

        List<ServiceDto> userServicesAfterSaveSecondOrder = userController.getUserServices(clientAccount.getEmail());

        assertEquals(2, userServicesAfterSaveSecondOrder.size());
    }

    @Test @Transactional
    void saveClient() {

    }

//    @Test @Transactional
//    void saveEmployee() {
//    }
//
//    @Test @Transactional
//    void editEmployeeData() {
//    }
//
//    @Test @Transactional
//    void editClientData() {
//    }

    private void testEmployee(EmployeeDto employee) {
        assertEquals("Alejandra", employee.getFirstName());
        assertEquals("Langworth", employee.getLastName());
        assertEquals("jarod.howell@yahoo.com", employee.getEmail());
        assertEquals("661412255", employee.getPhoneNumber());
        assertEquals("Kielce", employee.getWorkplace());
        assertEquals(12000.0, employee.getSalary());
        assertEquals(ContractForm.B2B, employee.getContractForm());
    }

    private void testClient(ClientDto clientDto) {
        assertEquals(clientAccount.getUuid(), clientDto.getUuid());
        assertEquals(clientAccount.getFirstName(), clientDto.getFirstName());
        assertEquals(clientAccount.getLastName(), clientDto.getLastName());
        assertEquals(clientAccount.getEmail(), clientDto.getEmail());
        assertEquals(clientAccount.getAddress(), clientDto.getAddress());
        assertEquals(clientAccount.getBalance(), clientDto.getBalance());
        assertEquals(clientAccount.getPhoneNumber(), clientDto.getPhoneNumber());
        assertEquals(clientAccount.isBusinessClient(), clientDto.isBusinessClient());
    }

}