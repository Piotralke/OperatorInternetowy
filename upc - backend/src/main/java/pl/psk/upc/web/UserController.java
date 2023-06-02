package pl.psk.upc.web;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.EmployeeEntity;
import pl.psk.upc.infrastructure.entity.ProductEntity;
import pl.psk.upc.infrastructure.entity.ProductType;
import pl.psk.upc.infrastructure.repository.ClientRepository;
import pl.psk.upc.infrastructure.repository.EmployeeRepository;

import java.util.List;

@RestController
public class UserController {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public UserController(ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(UpcRestPaths.GET_USER_DATA)
    public ClientAccountEntity getUserByEmail(@RequestParam String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @GetMapping(UpcRestPaths.GET_ALL_USERS)
    public List<ClientAccountEntity> getAllUsers() {
        return clientRepository.findAll();
    }

    @GetMapping(UpcRestPaths.GET_EMPLOYEE_DATA)
    public EmployeeEntity getEmployeeByEmail(@RequestParam String email) {
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @GetMapping(UpcRestPaths.GET_ALL_EMPLOYEES)
    public List<EmployeeEntity> getAllEmployees() {
        return employeeRepository.findAll();
    }

}
