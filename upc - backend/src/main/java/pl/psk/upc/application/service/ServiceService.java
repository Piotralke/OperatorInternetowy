package pl.psk.upc.application.service;

import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.web.service.ServiceDto;
import pl.psk.upc.web.service.ServiceDtoWrapper;

import java.util.UUID;

public interface ServiceService {
    ServiceDtoWrapper getServices();
    ServiceDto getService(UUID uuid);
    ServiceDtoWrapper getServicesByClient(ClientAccountEntity clientAccountEntity);
}
