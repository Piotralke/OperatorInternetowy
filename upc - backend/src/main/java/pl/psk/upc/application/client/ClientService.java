package pl.psk.upc.application.client;

import pl.psk.upc.web.user.ClientRegisterRequestDto;
import pl.psk.upc.web.user.ClientDto;
import pl.psk.upc.web.user.ClientDtoWrapper;
import pl.psk.upc.web.user.ClientEditRequestDto;

import java.util.UUID;

public interface ClientService {
    ClientDto findByEmail(String email);
    ClientDtoWrapper findAll();
    UUID save(ClientRegisterRequestDto registerRequestDto);
    UUID edit(ClientEditRequestDto clientEditRequestDto);
    ClientDto findByUuid(UUID uuid);
}
