package pl.psk.upc.web.user;

import org.springframework.web.bind.annotation.*;
import pl.psk.upc.application.client.ClientService;
import pl.psk.upc.application.employee.EmployeeService;
import pl.psk.upc.infrastructure.enums.ContractForm;
import pl.psk.upc.web.UpcRestPaths;
import pl.psk.upc.web.service.ServiceDto;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {

    private final ClientService clientService;
    private final EmployeeService employeeService;

    public UserController(ClientService clientService, EmployeeService employeeService) {
        this.clientService = clientService;
        this.employeeService = employeeService;
    }

    @GetMapping(UpcRestPaths.GET_USER_DATA)
    public ClientDto getUserByEmail(@RequestParam String email) {
        return clientService.findByEmail(email);
    }

    @GetMapping(UpcRestPaths.GET_USER_DATA_BY_UUID)
    public ClientDto getUserByEmail(@PathVariable(value = "uuid") UUID uuid) {
        return clientService.findByUuid(uuid);
    }

    @GetMapping(UpcRestPaths.GET_ALL_USERS)
    public ClientDtoWrapper getAllUsers() {
        return clientService.findAll();
    }

    @GetMapping(UpcRestPaths.GET_EMPLOYEE_DATA)
    public EmployeeDto getEmployeeByEmail(@RequestParam String email) {
        return employeeService.findByEmail(email);
    }

    @GetMapping(UpcRestPaths.GET_EMPLOYEE_DATA_BY_UUID)
    public EmployeeDto getEmployeeByEmail(@PathVariable(value = "uuid") UUID uuid) {
        return employeeService.findByUuid(uuid);
    }

    @GetMapping(UpcRestPaths.GET_ALL_EMPLOYEES)
    public EmployeeWrapper getAllEmployees() {
        return employeeService.findAll();
    }

    @GetMapping(UpcRestPaths.GET_USER_SERVICES)
    public List<ServiceDto> getUserServices(@PathVariable(value = "email") String email) {
        return clientService.findByEmail(email)
                .getServices();
    }

    @PostMapping(UpcRestPaths.CLIENT_REGISTER)
    public UUID saveClient(@RequestBody ClientRegisterRequestDto registerRequestDto) {
        return clientService.save(registerRequestDto);
    }

    @PostMapping(UpcRestPaths.EMPLOYEE_REGISTER)
    public UUID saveEmployee(@RequestBody EmployeeRegisterRequestDto registerRequestDto) {
        return employeeService.save(registerRequestDto);
    }

    @PutMapping(UpcRestPaths.EDIT_EMPLOYEE)
    public UUID editEmployeeData(@RequestBody EmployeeEditRequestDto employeeEditRequestDto) {
        return employeeService.edit(employeeEditRequestDto);
    }

    @PutMapping(UpcRestPaths.EDIT_CLIENT)
    public UUID editClientData(@RequestBody ClientEditRequestDto clientEditRequestDto) {
        return clientService.edit(clientEditRequestDto);
    }

    @GetMapping(UpcRestPaths.GET_CONTRACT_FORMS)
    public List<ContractForm> getContractForms() {
        return Arrays.stream(ContractForm.values())
                .toList();
    }
}
