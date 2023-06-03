package pl.psk.upc.application.employee;

import pl.psk.upc.infrastructure.dto.EmployeeRegisterRequestDto;
import pl.psk.upc.infrastructure.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeService {
    EmployeeEntity findByEmail(String email);
    List<EmployeeEntity> findAll();
    EmployeeEntity save(EmployeeRegisterRequestDto registerRequestDto);
}
