package pl.psk.upc.application.employee;

import pl.psk.upc.infrastructure.dto.EmployeeRegisterRequestDto;
import pl.psk.upc.web.user.EmployeeDto;
import pl.psk.upc.web.user.EmployeeWrapper;

import java.util.UUID;

public interface EmployeeService {
    EmployeeDto findByEmail(String email);
    EmployeeWrapper findAll();
    UUID save(EmployeeRegisterRequestDto registerRequestDto);
    EmployeeDto findByUuid(UUID uuid);
}
