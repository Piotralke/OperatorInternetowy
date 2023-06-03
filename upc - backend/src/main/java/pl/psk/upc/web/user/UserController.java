package pl.psk.upc.web.user;

import org.springframework.web.bind.annotation.*;
import pl.psk.upc.application.client.ClientConverter;
import pl.psk.upc.application.client.ClientService;
import pl.psk.upc.application.employee.EmployeeConverter;
import pl.psk.upc.application.employee.EmployeeService;
import pl.psk.upc.infrastructure.dto.ClientRegisterRequestDto;
import pl.psk.upc.infrastructure.dto.EmployeeRegisterRequestDto;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.EmployeeEntity;
import pl.psk.upc.infrastructure.entity.ServiceEntity;
import pl.psk.upc.web.UpcRestPaths;

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
        ClientAccountEntity user = clientService.findByEmail(email);
        return ClientConverter.convertFrom(user);

    }

    @GetMapping(UpcRestPaths.GET_ALL_USERS)
    public ClientDtoWrapper getAllUsers() {
        return ClientConverter.convertFrom(clientService.findAll());
    }

    @GetMapping(UpcRestPaths.GET_EMPLOYEE_DATA)
    public EmployeeDto getEmployeeByEmail(@RequestParam String email) {
        EmployeeEntity user = employeeService.findByEmail(email);
        return EmployeeConverter.convertFrom(user);
    }

    @GetMapping(UpcRestPaths.GET_ALL_EMPLOYEES)
    public EmployeeWrapper getAllEmployees() {
        return EmployeeConverter.convertFrom(employeeService.findAll());
    }

    @GetMapping(UpcRestPaths.GET_USER_SERVICES)
    public List<ServiceEntity> getUserServices(@PathVariable(value = "email") String email) {
        ClientAccountEntity user = clientService.findByEmail(email);
        return ClientConverter.convertFrom(user)
                .getServices();
    }

    @PostMapping(UpcRestPaths.CLIENT_REGISTER)
    public UUID saveClient(@RequestBody ClientRegisterRequestDto registerRequestDto) {
        return clientService.save(registerRequestDto)
                .getUuid();
    }

    @PostMapping(UpcRestPaths.EMPLOYEE_REGISTER)
    public UUID saveEmployee(@RequestBody EmployeeRegisterRequestDto registerRequestDto) {
        return employeeService.save(registerRequestDto)
                .getUuid();
    }

}
