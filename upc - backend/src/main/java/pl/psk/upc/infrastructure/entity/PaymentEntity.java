package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "Payments")
@Builder
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long payment_id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "data")
    ZonedDateTime date;

    @Column(name = "amount")
    double amount;

    @Column(name = "is_payment_completed")
    boolean isPaymentCompleted;


}
