package pl.psk.upc.application.warehouse;

import pl.psk.upc.web.warehouse.WarehouseDto;
import pl.psk.upc.web.warehouse.WarehouseDtoWrapper;

import java.util.UUID;


public interface WarehouseService {
    WarehouseDtoWrapper getAll();
    WarehouseDto getByUuid(UUID uuid);
}
