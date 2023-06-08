package pl.psk.upc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.psk.upc.infrastructure.dto.Account;
import pl.psk.upc.infrastructure.entity.EmployeeEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByEmail(String email);
    Optional<EmployeeEntity> findByUuid(UUID uuid);
}
