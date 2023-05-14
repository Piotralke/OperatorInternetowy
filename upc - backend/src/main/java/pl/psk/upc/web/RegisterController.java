package pl.psk.upc.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.psk.upc.infrastructure.dto.ClientRegisterRequestDto;
import pl.psk.upc.infrastructure.dto.EmployeeRegisterRequestDto;

@RestController
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping(UpcRestPaths.CLIENT_REGISTER)
    public void clientRegister(@RequestBody ClientRegisterRequestDto registerRequestDto) {
        registerService.registerClient(registerRequestDto);
    }

    @PostMapping(UpcRestPaths.EMPLOYEE_REGISTER)
    public void employeeRegister(@RequestBody EmployeeRegisterRequestDto registerRequestDto) {
        registerService.registerEmployee(registerRequestDto);
    }

}
