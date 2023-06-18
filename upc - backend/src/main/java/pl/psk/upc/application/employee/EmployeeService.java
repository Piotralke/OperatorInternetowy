package pl.psk.upc.application.employee;

import pl.psk.upc.web.user.*;

import java.util.UUID;

public interface EmployeeService {
    EmployeeDto findByEmail(String email);
    EmployeeWrapper findAll();
    UUID save(EmployeeRegisterRequestDto registerRequestDto);
    UUID edit(EmployeeEditRequestDto employeeEditRequestDto);
    UUID editPassword(EmployeeDtoPasswordEditRequest employeeEditRequestDto);
    EmployeeDto findByUuid(UUID uuid);
}
