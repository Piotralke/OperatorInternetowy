package pl.psk.upc.application.employee;

import pl.psk.upc.infrastructure.dto.EmployeeRegisterRequestDto;
import pl.psk.upc.infrastructure.entity.EmployeeEntity;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {
    EmployeeEntity findByEmail(String email);
    List<EmployeeEntity> findAll();
    EmployeeEntity save(EmployeeRegisterRequestDto registerRequestDto);
    EmployeeEntity findByUuid(UUID uuid);
}
