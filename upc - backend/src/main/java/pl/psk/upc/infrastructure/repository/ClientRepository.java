package pl.psk.upc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.psk.upc.infrastructure.dto.Account;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientAccountEntity, Long> {

    Optional<ClientAccountEntity> findByEmail(String email);

}
