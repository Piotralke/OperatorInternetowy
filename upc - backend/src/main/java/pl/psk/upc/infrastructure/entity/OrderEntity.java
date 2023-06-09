package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "orders")
@Builder
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long order_id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "order_date")
    ZonedDateTime orderDate;

    @Column(name = "order_status")
    OrderStatus orderStatus;

    @Column(name = "amount")
    Double amount;

    @Column(name = "payment_status")
    PaymentStatus paymentStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    EmployeeEntity employeeEntity;

    @OneToMany(fetch = FetchType.EAGER)
    List<ProductEntity> productEntities;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_id")
    ServiceEntity service;

}
