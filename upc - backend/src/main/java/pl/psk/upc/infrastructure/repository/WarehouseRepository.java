package pl.psk.upc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.psk.upc.infrastructure.entity.WarehouseEntity;

import java.util.UUID;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
    WarehouseEntity findByUuid(UUID uuid);

}
