package pl.psk.upc.application.service;

import org.springframework.stereotype.Service;
import pl.psk.upc.exception.GenericNotFoundException;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.ServiceEntity;
import pl.psk.upc.infrastructure.repository.ServiceRepository;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.service.ServiceDto;
import pl.psk.upc.web.service.ServiceDtoWrapper;

import java.util.List;
import java.util.UUID;

@Service
class ServiceServiceImpl implements ServiceService {
    private final static String NOT_FOUND_MESSAGE = "Service not found";

    private final ServiceRepository serviceRepository;

    public ServiceServiceImpl(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    public ServiceDtoWrapper getServices() {
        return ServiceConverter.convertFrom(serviceRepository.findAll());
    }

    @Override
    public ServiceDto getService(UUID uuid) {
        MethodArgumentValidator.requiredNotNull(uuid, "uuid");
        ServiceEntity service = serviceRepository.findByUuid(uuid)
                .orElseThrow(() -> new GenericNotFoundException(NOT_FOUND_MESSAGE));
        return ServiceConverter.convertFrom(service);
    }

    @Override
    public ServiceDtoWrapper getServicesByClient(ClientAccountEntity clientAccountEntity) {
        List<ServiceEntity> servicesByClientAccountEntity = serviceRepository.findServicesByClientAccountEntity(clientAccountEntity);
        return ServiceConverter.convertFrom(servicesByClientAccountEntity);
    }
}
