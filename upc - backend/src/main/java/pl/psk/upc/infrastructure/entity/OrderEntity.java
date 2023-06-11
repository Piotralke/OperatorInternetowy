package pl.psk.upc.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    ClientAccountEntity clientAccountEntity;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL)
    List<ProductEntity> productEntities;

    @OneToOne
    ServiceEntity service;

}
