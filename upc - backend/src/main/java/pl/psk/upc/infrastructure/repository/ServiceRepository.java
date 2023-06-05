package pl.psk.upc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.psk.upc.infrastructure.entity.ServiceEntity;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {

    ServiceEntity findByUuid(UUID uuid);
}
