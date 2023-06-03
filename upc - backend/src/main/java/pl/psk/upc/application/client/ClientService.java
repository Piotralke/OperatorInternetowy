package pl.psk.upc.application.client;

import pl.psk.upc.infrastructure.dto.ClientRegisterRequestDto;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;

import java.util.List;

public interface ClientService {
    ClientAccountEntity findByEmail(String email);
    List<ClientAccountEntity> findAll();
    ClientAccountEntity save(ClientRegisterRequestDto registerRequestDto);
}
