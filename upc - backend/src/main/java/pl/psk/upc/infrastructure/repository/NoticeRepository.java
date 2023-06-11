package pl.psk.upc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.psk.upc.infrastructure.entity.NoticeEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {
    List<NoticeEntity> findAllByClientAccountEntity_Uuid(UUID clientAccountEntityId);
    Optional<NoticeEntity> findByUuid(UUID uuid);
}
