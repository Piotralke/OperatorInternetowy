package pl.psk.upc.application.client;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.psk.upc.application.service.ServiceService;
import pl.psk.upc.exception.GenericException;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.enums.RoleEnum;
import pl.psk.upc.infrastructure.repository.ClientRepository;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.service.ServiceDtoWrapper;
import pl.psk.upc.web.user.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
class ClientServiceImpl implements ClientService {
    private final static String NOT_FOUND_MESSAGE = "User not found";

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final ServiceService serviceService;


    public ClientServiceImpl(ClientRepository clientRepository, PasswordEncoder passwordEncoder, ServiceService serviceService) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
        this.serviceService = serviceService;
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
    public ServiceDtoWrapper findAllClientServices(String email) {
        ClientAccountEntity client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_MESSAGE));

        return serviceService.getServicesByClient(client);
    }

    @Override
    public UUID save(ClientRegisterRequestDto registerRequestDto) {
        MethodArgumentValidator.requiredNotNull(registerRequestDto, "registerRequestDto");
        Optional<ClientAccountEntity> optionalClientAccount = clientRepository.findByEmail(registerRequestDto.getEmail());

        if (optionalClientAccount.isPresent()) {
            throw new GenericException("Client with email: " + registerRequestDto.getEmail() + "exist");
        }

        ClientAccountEntity accountEntity = ClientAccountEntity.builder()
                .uuid(UUID.randomUUID())
                .firstName(registerRequestDto.getFirstName())
                .lastName(registerRequestDto.getLastName())
                .email(registerRequestDto.getEmail())
                .password(passwordEncoder.encode(registerRequestDto.getPassword()))
                .address(registerRequestDto.getAddress())
                .balance(0L)
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
        client.setPhoneNumber(clientEditRequestDto.getPhoneNumber());
        client.setNip(clientEditRequestDto.getNip());
        client.setBusinessClient(clientEditRequestDto.isBusinessClient());

        return clientRepository.save(client)
                .getUuid();
    }

    @Override
    public UUID editClientPassword(ClientDtoPasswordEditRequest clientEditRequestDto) {
        MethodArgumentValidator.requiredNotNull(clientEditRequestDto, "clientEditRequestDto");
        ClientAccountEntity client = clientRepository.findByUuid(clientEditRequestDto.getUuid())
                .orElseThrow(() -> new UsernameNotFoundException(NOT_FOUND_MESSAGE));

        client.setPassword(passwordEncoder.encode(clientEditRequestDto.getPassword()));

        return clientRepository.save(client)
                .getUuid();
    }
}
