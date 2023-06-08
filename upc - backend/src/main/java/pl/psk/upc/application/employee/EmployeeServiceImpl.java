package pl.psk.upc.application.employee;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.dto.EmployeeRegisterRequestDto;
import pl.psk.upc.infrastructure.entity.EmployeeEntity;
import pl.psk.upc.infrastructure.enums.RoleEnum;
import pl.psk.upc.infrastructure.repository.EmployeeRepository;
import pl.psk.upc.web.user.EmployeeDto;
import pl.psk.upc.web.user.EmployeeWrapper;

import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EmployeeDto findByEmail(String email) {
        EmployeeEntity employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return EmployeeConverter.convertFrom(employee);
    }

    @Override
    public EmployeeDto findByUuid(UUID uuid) {
        EmployeeEntity employee = employeeRepository.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return EmployeeConverter.convertFrom(employee);
    }

    @Override
    public EmployeeWrapper findAll() {
        return EmployeeConverter.convertFrom(employeeRepository.findAll());
    }

    @Override
    public UUID save(EmployeeRegisterRequestDto registerRequestDto) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findByEmail(registerRequestDto.getEmail());

        UUID uuid = null;
        if (optionalEmployee.isPresent()) {
            uuid = optionalEmployee.get().getUuid();
        } else {
            uuid = UUID.randomUUID();
        }

        EmployeeEntity accountEntity = EmployeeEntity.builder()
                .uuid(uuid)
                .firstName(registerRequestDto.getFirstName())
                .lastName(registerRequestDto.getLastName())
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .address(registerRequestDto.getAddress())
                .accountStatus(registerRequestDto.getAccountStatus())
                .roles(RoleEnum.USER.name())
                .phoneNumber(registerRequestDto.getPhoneNumber())
                .workplace(registerRequestDto.getWorkplace())
                .salary(registerRequestDto.getSalary())
                .contractForm(registerRequestDto.getContractForm())
                .build();

        return employeeRepository.save(accountEntity)
                .getUuid();
    }
}
