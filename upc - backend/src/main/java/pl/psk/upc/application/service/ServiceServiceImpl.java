package pl.psk.upc.application.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.entity.ServiceEntity;
import pl.psk.upc.infrastructure.repository.ServiceRepository;
import pl.psk.upc.web.service.ServiceDto;
import pl.psk.upc.web.service.ServiceDtoWrapper;

import java.util.UUID;

@Service
public class ServiceServiceImpl implements ServiceService {

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
        ServiceEntity service = serviceRepository.findByUuid(uuid)
                .orElseThrow(() -> new UsernameNotFoundException("Service not found"));
        return ServiceConverter.convertFrom(service);
    }
}
