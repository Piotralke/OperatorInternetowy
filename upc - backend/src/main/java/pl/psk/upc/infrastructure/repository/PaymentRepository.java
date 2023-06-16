package pl.psk.upc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.psk.upc.infrastructure.entity.PaymentEntity;
import pl.psk.upc.infrastructure.enums.PaymentStatus;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    Optional<PaymentEntity> findByUuid(UUID uuid);
    List<PaymentEntity> findAllByPaymentStatus(PaymentStatus paymentStatus);
}
