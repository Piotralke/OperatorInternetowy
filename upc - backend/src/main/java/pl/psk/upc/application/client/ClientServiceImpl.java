package pl.psk.upc.application.client;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.dto.ClientRegisterRequestDto;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.enums.RoleEnum;
import pl.psk.upc.infrastructure.repository.ClientRepository;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.user.ClientDto;
import pl.psk.upc.web.user.ClientDtoWrapper;
import pl.psk.upc.web.user.ClientEditRequestDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
class ClientServiceImpl implements ClientService {
    private final static String NOT_FOUND_MESSAGE = "User not found";

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;


    public ClientServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClientDto findByEmail(String email) {
        MethodArgumentValidator.requiredNotNullOrBlankString(email, "email");
        ClientAccountEntity client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_MESSAGE));
        return ClientConverter.convertFrom(client);
    }

    @Override
    public ClientDto findByUuid(UUID uuid) {
        MethodArgumentValidator.requiredNotNull(uuid, "uuid");
        ClientAccountEntity client = clientRepository.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_MESSAGE));
        return ClientConverter.convertFrom(client);
    }

    @Override
    public ClientDtoWrapper findAll() {
        List<ClientAccountEntity> clients = clientRepository.findAll();
        return ClientConverter.convertFrom(clients);
    }

    @Override
    public UUID save(ClientRegisterRequestDto registerRequestDto) {
        MethodArgumentValidator.requiredNotNull(registerRequestDto, "registerRequestDto");
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

        return clientRepository.save(accountEntity)
                .getUuid();
    }

    @Override
    public UUID edit(ClientEditRequestDto clientEditRequestDto) {
        MethodArgumentValidator.requiredNotNull(clientEditRequestDto, "clientEditRequestDto");
        ClientAccountEntity client = clientRepository.findByUuid(clientEditRequestDto.getUuid())
                .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_MESSAGE));

        client.setFirstName(clientEditRequestDto.getFirstName());
        client.setLastName(clientEditRequestDto.getLastName());
        client.setAddress(clientEditRequestDto.getAddress());
        client.setBalance(clientEditRequestDto.getBalance());
        client.setAccountStatus(clientEditRequestDto.getAccountStatus());
        client.setPhoneNumber(clientEditRequestDto.getPhoneNumber());
        client.setNip(clientEditRequestDto.getNip());
        client.setBusinessClient(clientEditRequestDto.isBusinessClient());

        return clientRepository.save(client)
                .getUuid();
    }
}
