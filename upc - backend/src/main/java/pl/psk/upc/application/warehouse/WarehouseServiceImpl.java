package pl.psk.upc.application.warehouse;

import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.entity.WarehouseEntity;
import pl.psk.upc.infrastructure.repository.WarehouseRepository;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.warehouse.WarehouseDto;
import pl.psk.upc.web.warehouse.WarehouseDtoWrapper;

import java.util.UUID;

@Service
class WarehouseServiceImpl implements WarehouseService{

    private final WarehouseRepository warehouseRepository;

    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public WarehouseDtoWrapper getAll() {
        return WarehouseConverter.convertFrom(warehouseRepository.findAll());
    }

    @Override
    public WarehouseDto getByUuid(UUID uuid) {
        MethodArgumentValidator.requiredNotNull(uuid, "uuid");
        WarehouseEntity warehouse = warehouseRepository.findByUuid(uuid);
        return WarehouseConverter.convertFrom(warehouse);
    }
}
