package pl.psk.upc.infrastructure.repository;//package pl.psk.upc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.psk.upc.infrastructure.entity.ContractEntity;

public interface ContractRepository extends JpaRepository<ContractEntity, Long> {
}
