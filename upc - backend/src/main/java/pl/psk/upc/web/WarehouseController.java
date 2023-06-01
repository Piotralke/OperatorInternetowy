package pl.psk.upc.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.psk.upc.infrastructure.entity.WarehouseEntity;
import pl.psk.upc.infrastructure.repository.WarehouseRepository;

import java.util.List;
import java.util.UUID;

@RestController
public class WarehouseController {

    private final WarehouseRepository warehouseRepository;

    public WarehouseController(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @GetMapping(UpcRestPaths.GET_ALL_FROM_WAREHOUSE)
    public List<WarehouseEntity> getAll() {
        return warehouseRepository.findAll();
    }

    @GetMapping(UpcRestPaths.GET_FROM_WAREHOUSE_BY_UUID)
    public WarehouseEntity getByUuid(@PathVariable(value = "uuid") UUID uuid) {
        return warehouseRepository.findByUuid(uuid);
    }
}
