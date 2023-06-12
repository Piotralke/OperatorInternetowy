package pl.psk.upc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<ClientAccountEntity, Long> {

    Optional<ClientAccountEntity> findByEmail(String email);
    Optional<ClientAccountEntity> findByUuid(UUID uuid);

}
