package pl.psk.upc.web.integration.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.psk.upc.exception.GenericException;
import pl.psk.upc.infrastructure.enums.ContractForm;
import pl.psk.upc.web.UpcTest;
import pl.psk.upc.web.order.OrderController;
import pl.psk.upc.web.service.ServiceDto;
import pl.psk.upc.web.user.*;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest

class UserControllerTest extends UpcTest {

    @Autowired
    UserController userController;

    @Autowired
    OrderController orderController;

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
        EmployeeDto employee = userController.getEmployeeByUuid(EMPLOYEE_UUID);

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
        EmployeeDto employee = userController.getEmployeeByEmail(EMPLOYEE_EMAIL);

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

    @Test
    @Transactional
    void saveClient() {
        UUID uuid = userController.saveClient(clientRegisterRequestDto);
        ClientDto userByUuid = userController.getUserByUuid(uuid);

        assertEquals(uuid, userByUuid.getUuid());
        assertEquals(clientRegisterRequestDto.getFirstName(), userByUuid.getFirstName());
        assertEquals(clientRegisterRequestDto.getLastName(), userByUuid.getLastName());
        assertEquals(clientRegisterRequestDto.getEmail(), userByUuid.getEmail());
        assertEquals(clientRegisterRequestDto.getAddress(), userByUuid.getAddress());
        assertEquals(clientRegisterRequestDto.getPhoneNumber(), userByUuid.getPhoneNumber());
        assertEquals(clientRegisterRequestDto.getNip(), userByUuid.getNip());
        assertEquals(clientRegisterRequestDto.getPesel(), userByUuid.getPesel());
        assertEquals(clientRegisterRequestDto.isBusinessClient(), userByUuid.isBusinessClient());
    }

    @Test
    @Transactional
    void saveExistedClient() {
        assertThrows(GenericException.class, () -> userController.saveClient(clientRegisterRequestDto.toBuilder().email(clientAccount.getEmail()).build()));
    }

    @Test
    @Transactional
    void saveExistedEmployee() {
        assertThrows(GenericException.class, () -> userController.saveEmployee(employeeRegisterRequestDto.toBuilder().email(EMPLOYEE_EMAIL).build()));
    }

    @Test
    @Transactional
    void saveEmployee() {
        UUID uuid = userController.saveEmployee(employeeRegisterRequestDto);
        EmployeeDto employeeByUuid = userController.getEmployeeByUuid(uuid);

        assertEquals(uuid, employeeByUuid.getUuid());
        assertEquals(employeeRegisterRequestDto.getFirstName(), employeeByUuid.getFirstName());
        assertEquals(employeeRegisterRequestDto.getLastName(), employeeByUuid.getLastName());
        assertEquals(employeeRegisterRequestDto.getEmail(), employeeByUuid.getEmail());
        assertEquals(employeeRegisterRequestDto.getAddress(), employeeByUuid.getAddress());
        assertEquals(employeeRegisterRequestDto.getWorkplace(), employeeByUuid.getWorkplace());
        assertEquals(employeeRegisterRequestDto.getSalary(), employeeByUuid.getSalary());
        assertEquals(employeeRegisterRequestDto.getContractForm(), employeeByUuid.getContractForm());
        assertEquals(employeeRegisterRequestDto.getPhoneNumber(), employeeByUuid.getPhoneNumber());
        assertEquals(employeeRegisterRequestDto.getNip(), employeeByUuid.getNip());
        assertEquals(employeeRegisterRequestDto.getPesel(), employeeByUuid.getPesel());
    }

    @Test
    @Transactional
    void editEmployeeData() {
        userController.editEmployeeData(employeeEditRequestDto);
        EmployeeDto employee = userController.getEmployeeByEmail(EMPLOYEE_EMAIL);

        assertEquals(employeeEditRequestDto.getUuid(), employee.getUuid());
        assertEquals(employeeEditRequestDto.getFirstName(), employee.getFirstName());
        assertEquals(employeeEditRequestDto.getLastName(), employee.getLastName());
        assertEquals(EMPLOYEE_EMAIL, employee.getEmail());
        assertEquals(employeeEditRequestDto.getPhoneNumber(), employee.getPhoneNumber());
        assertEquals(employeeEditRequestDto.getWorkplace(), employee.getWorkplace());
        assertEquals(employeeEditRequestDto.getSalary(), employee.getSalary());
        assertEquals(employeeEditRequestDto.getContractForm(), employee.getContractForm());
        assertEquals(employeeEditRequestDto.getNip(), employee.getNip());
    }

    @Test
    @Transactional
    void editClientData() {
        UUID uuid = userController.editClientData(clientEditRequestDto);
        ClientDto userByUuid = userController.getUserByUuid(uuid);

        assertEquals(uuid, userByUuid.getUuid());
        assertEquals(clientEditRequestDto.getFirstName(), userByUuid.getFirstName());
        assertEquals(clientEditRequestDto.getLastName(), userByUuid.getLastName());
        assertEquals(clientEditRequestDto.getAddress(), userByUuid.getAddress());
        assertEquals(clientEditRequestDto.getBalance(), userByUuid.getBalance());
        assertEquals(clientEditRequestDto.getPhoneNumber(), userByUuid.getPhoneNumber());
        assertEquals(clientEditRequestDto.getNip(), userByUuid.getNip());
        assertEquals(clientEditRequestDto.isBusinessClient(), userByUuid.isBusinessClient());
    }

    private void testEmployee(EmployeeDto employee) {
        assertEquals("Alejandra", employee.getFirstName());
        assertEquals("Langworth", employee.getLastName());
        assertEquals(EMPLOYEE_EMAIL, employee.getEmail());
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