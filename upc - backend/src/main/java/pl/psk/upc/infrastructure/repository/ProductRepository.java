package pl.psk.upc.infrastructure.repository;//package pl.psk.upc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.psk.upc.infrastructure.entity.ProductEntity;
import pl.psk.upc.infrastructure.enums.ProductType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByProductType(ProductType productType);
    Optional<ProductEntity> findByUuid(UUID uuid);
}
