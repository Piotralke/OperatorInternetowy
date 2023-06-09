package pl.psk.upc.application.client;

import pl.psk.upc.infrastructure.dto.ClientRegisterRequestDto;
import pl.psk.upc.web.user.ClientDto;
import pl.psk.upc.web.user.ClientDtoWrapper;

import java.util.UUID;

public interface ClientService {
    ClientDto findByEmail(String email);
    ClientDtoWrapper findAll();
    UUID save(ClientRegisterRequestDto registerRequestDto);
    ClientDto findByUuid(UUID uuid);
}
