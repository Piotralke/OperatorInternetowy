package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Entity
@Table(name = "Payments")
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long payment_id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "data")
    LocalDate date;

    @Column(name = "amount")
    double amount;

    @Column(name = "is_payment_completed")
    boolean isPaymentCompleted;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    ContractEntity contractEntity;
}
