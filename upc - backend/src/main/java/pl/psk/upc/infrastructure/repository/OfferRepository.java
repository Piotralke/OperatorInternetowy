package pl.psk.upc.infrastructure.repository;//package pl.psk.upc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.psk.upc.infrastructure.entity.OfferEntity;
import pl.psk.upc.infrastructure.enums.OfferType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

    List<OfferEntity> findByOfferType(OfferType offerType);
    Optional<OfferEntity> findByUuid(UUID uuid);
}
