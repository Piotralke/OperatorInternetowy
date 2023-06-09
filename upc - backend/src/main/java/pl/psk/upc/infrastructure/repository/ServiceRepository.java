package pl.psk.upc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.psk.upc.infrastructure.entity.ServiceEntity;

import java.util.Optional;
import java.util.UUID;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    Optional<ServiceEntity> findByUuid(UUID uuid);
}
