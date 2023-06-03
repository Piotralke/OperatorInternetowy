package pl.psk.upc.web.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.psk.upc.infrastructure.entity.ServiceEntity;
import pl.psk.upc.infrastructure.repository.ServiceRepository;
import pl.psk.upc.web.UpcRestPaths;

import java.util.List;
import java.util.UUID;

@RestController
public class ServiceController {

    private final ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @GetMapping(UpcRestPaths.GET_ALL_SERVICES)
    public List<ServiceEntity> getServices() {
        return serviceRepository.findAll();
    }

    @GetMapping(UpcRestPaths.GET_SERVICE)
    public ServiceEntity getService(@PathVariable(value = "uuid") UUID uuid) {
        return serviceRepository.findByUuid(uuid);
    }
}
