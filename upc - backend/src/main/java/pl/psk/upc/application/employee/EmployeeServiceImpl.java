package pl.psk.upc.application.employee;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.psk.upc.exception.GenericException;
import pl.psk.upc.web.user.EmployeeEditRequestDto;
import pl.psk.upc.web.user.EmployeeRegisterRequestDto;
import pl.psk.upc.infrastructure.entity.EmployeeEntity;
import pl.psk.upc.infrastructure.enums.RoleEnum;
import pl.psk.upc.infrastructure.repository.EmployeeRepository;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.user.EmployeeDto;
import pl.psk.upc.web.user.EmployeeWrapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
class EmployeeServiceImpl implements EmployeeService {
    private final static String NOT_FOUND_MESSAGE = "User not found";

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;


    public EmployeeServiceImpl(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public EmployeeDto findByEmail(String email) {
        MethodArgumentValidator.requiredNotNullOrBlankString(email, "email");
        EmployeeEntity employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_MESSAGE));
        return EmployeeConverter.convertFrom(employee);
    }

    @Override
    public EmployeeDto findByUuid(UUID uuid) {
        MethodArgumentValidator.requiredNotNull(uuid, "uuid)");
        EmployeeEntity employee = employeeRepository.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_MESSAGE));
        return EmployeeConverter.convertFrom(employee);
    }

    @Override
    public EmployeeWrapper findAll() {
        return EmployeeConverter.convertFrom(employeeRepository.findAll());
    }

    @Override
    public UUID save(EmployeeRegisterRequestDto registerRequestDto) {
        MethodArgumentValidator.requiredNotNull(registerRequestDto, "registerRequestDto");
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findByEmail(registerRequestDto.getEmail());

        if (optionalEmployee.isPresent()) {
            throw new GenericException("Employee with email: " + registerRequestDto.getEmail() + "exist");
        }

        EmployeeEntity accountEntity = EmployeeEntity.builder()
                .uuid(UUID.randomUUID())
                .firstName(registerRequestDto.getFirstName())
                .lastName(registerRequestDto.getLastName())
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .roles(RoleEnum.WORKER.name())
                .address(registerRequestDto.getAddress())
                .phoneNumber(registerRequestDto.getPhoneNumber())
                .workplace(registerRequestDto.getWorkplace())
                .salary(registerRequestDto.getSalary())
                .contractForm(registerRequestDto.getContractForm())
                .nip(registerRequestDto.getNip())
                .pesel(registerRequestDto.getPesel())
                .build();

        return employeeRepository.save(accountEntity)
                .getUuid();
    }

    @Override
    public UUID edit(EmployeeEditRequestDto employeeEditRequestDto) {
        MethodArgumentValidator.requiredNotNull(employeeEditRequestDto, "employeeEditRequestDto)");

        EmployeeEntity employee = employeeRepository.findByUuid(employeeEditRequestDto.getUuid())
                .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_MESSAGE));

        employee.setFirstName(employeeEditRequestDto.getFirstName());
        employee.setLastName(employeeEditRequestDto.getLastName());
        employee.setPassword(passwordEncoder.encode(employeeEditRequestDto.getPassword()));
        employee.setAddress(employeeEditRequestDto.getAddress());
        employee.setPhoneNumber(employeeEditRequestDto.getPhoneNumber());
        employee.setWorkplace(employeeEditRequestDto.getWorkplace());
        employee.setSalary(employeeEditRequestDto.getSalary());
        employee.setContractForm(employeeEditRequestDto.getContractForm());
        employee.setNip(employeeEditRequestDto.getNip());

        return employeeRepository.save(employee)
                .getUuid();
    }
}
