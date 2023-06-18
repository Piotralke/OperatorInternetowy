package pl.psk.upc.application.client;

import pl.psk.upc.web.service.ServiceDtoWrapper;
import pl.psk.upc.web.user.*;

import java.util.UUID;

public interface ClientService {
    ClientDto findByEmail(String email);
    ClientDtoWrapper findAll();
    ServiceDtoWrapper findAllClientServices(String email);
    UUID save(ClientRegisterRequestDto registerRequestDto);
    UUID edit(ClientEditRequestDto clientEditRequestDto);
    UUID editClientPassword(ClientDtoPasswordEditRequest clientEditRequestDto);
    ClientDto findByUuid(UUID uuid);
}
