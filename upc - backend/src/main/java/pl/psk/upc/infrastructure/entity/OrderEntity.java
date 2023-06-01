package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long order_id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "order_date")
    LocalDate orderDate;

    @Column(name = "order_status")
    OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    ClientAccountEntity clientAccountEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    EmployeeEntity employeeEntity;

    @OneToMany
    List<ProductEntity> productEntities;

    @OneToMany
    @JoinColumn(name = "service_id")
    List<ServiceEntity> serviceEntities;

}
