package pl.psk.upc.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import pl.psk.upc.infrastructure.enums.PaymentStatus;

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

    @Column(name = "name")
    String name;

    @Column(name = "data")
    ZonedDateTime date;

    @Column(name = "amount")
    double amount;

    @Column(name = "payment_status")
    PaymentStatus paymentStatus;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contract_id")
    ContractEntity contractEntity;

    @Column(name = "productsUuid")
    String productsUuid;

}
