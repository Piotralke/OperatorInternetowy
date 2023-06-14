package pl.psk.upc.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.EmployeeEntity;
import pl.psk.upc.infrastructure.entity.OrderEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> getOrdersByEmployeeEntity(EmployeeEntity employeeEntity);
    List<OrderEntity> getOrdersByClientAccountEntity(ClientAccountEntity clientAccountEntity);
    Optional<OrderEntity> findByUuid(UUID uuid);

}
