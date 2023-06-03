package pl.psk.upc.application.client;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.dto.ClientRegisterRequestDto;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.enums.RoleEnum;
import pl.psk.upc.infrastructure.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;


    public ClientServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClientAccountEntity findByEmail(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<ClientAccountEntity> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public ClientAccountEntity save(ClientRegisterRequestDto registerRequestDto) {
        Optional<ClientAccountEntity> optionalClientAccount = clientRepository.findByEmail(registerRequestDto.getEmail());
        UUID uuid = null;

        if (optionalClientAccount.isPresent()) {
            uuid = optionalClientAccount.get().getUuid();
        } else {
            uuid = UUID.randomUUID();
        }

        ClientAccountEntity accountEntity = ClientAccountEntity.builder()
                .uuid(uuid)
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

        return clientRepository.save(accountEntity);
    }
}
