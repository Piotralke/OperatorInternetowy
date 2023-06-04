package pl.psk.upc.web.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.psk.upc.application.service.ServiceService;
import pl.psk.upc.web.UpcRestPaths;

import java.util.UUID;

@RestController
public class ServiceController {

    private final ServiceService service;

    public ServiceController(ServiceService service) {
        this.service = service;
    }

    @GetMapping(UpcRestPaths.GET_ALL_SERVICES)
    public ServiceDtoWrapper getServices() {
        return service.getServices();
    }

    @GetMapping(UpcRestPaths.GET_SERVICE)
    public ServiceDto getService(@PathVariable(value = "uuid") UUID uuid) {
        return service.getService(uuid);
    }
}
