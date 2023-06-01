package pl.psk.upc.web;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import pl.psk.upc.infrastructure.dto.ClientRegisterRequestDto;
import pl.psk.upc.infrastructure.dto.EmployeeRegisterRequestDto;
import pl.psk.upc.infrastructure.dto.RoleEnum;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.EmployeeEntity;
import pl.psk.upc.infrastructure.repository.ClientRepository;
import pl.psk.upc.infrastructure.repository.EmployeeRepository;

import java.util.UUID;

@Service
public class RegisterService {

    private final PasswordEncoder passwordEncoder;
    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;

    public RegisterService(PasswordEncoder passwordEncoder, ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.passwordEncoder = passwordEncoder;
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    public String registerClient(@RequestBody ClientRegisterRequestDto registerRequestDto) {
        ClientAccountEntity newAccountEntity = ClientAccountEntity.builder()
                .uuid(UUID.randomUUID())
                .firstName(registerRequestDto.getFirstName())
                .lastName(registerRequestDto.getLastName())
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .address(registerRequestDto.getAddress())
                .balance(0L)
                .accountStatus(registerRequestDto.getAccountStatus())
                .roles(RoleEnum.USER.name())
                .phoneNumber(registerRequestDto.getPhoneNumber())
                .nip(registerRequestDto.getNip())
                .pesel(registerRequestDto.getPesel())
                .isBusinessClient(registerRequestDto.isBusinessClient())
                .build();

        ClientAccountEntity saved = clientRepository.save(newAccountEntity);
        return saved.getUuid().toString();
    }

    public String registerEmployee(@RequestBody EmployeeRegisterRequestDto registerRequestDto) {
        EmployeeEntity newAccountEntity = EmployeeEntity.builder()
                .uuid(UUID.randomUUID())
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

        EmployeeEntity saved = employeeRepository.save(newAccountEntity);
        return saved.getUuid().toString();
    }
}
