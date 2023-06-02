package pl.psk.upc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.UserProblemEntity;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserProblemRepository extends JpaRepository<UserProblemEntity, Long> {
    List<UserProblemEntity> findByClientAccountEntity(ClientAccountEntity clientAccountEntity);
    UserProblemEntity findByUuid(UUID uuid);
}