package pl.psk.upc.web.warehouse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.psk.upc.application.warehouse.WarehouseService;
import pl.psk.upc.infrastructure.entity.WarehouseEntity;
import pl.psk.upc.web.UpcRestPaths;

import java.util.List;
import java.util.UUID;

@RestController
public class WarehouseController {

    private final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping(UpcRestPaths.GET_ALL_FROM_WAREHOUSE)
    public WarehouseDtoWrapper getAll() {
        return warehouseService.getAll();
    }

    @GetMapping(UpcRestPaths.GET_FROM_WAREHOUSE_BY_UUID)
    public WarehouseDto getByUuid(@PathVariable(value = "uuid") UUID uuid) {
        return warehouseService.getByUuid(uuid);
    }
}
