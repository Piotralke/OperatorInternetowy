package pl.psk.upc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.psk.upc.infrastructure.entity.ContractEntity;

import java.util.Optional;
import java.util.UUID;

public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
    Optional<ContractEntity> findByUuid(UUID uuid);
}
