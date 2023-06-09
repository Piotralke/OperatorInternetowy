package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "contracts")
@Builder
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class ContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long contract_id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "start_date")
    ZonedDateTime startDate;

    @Column(name = "end_date")
    ZonedDateTime endDate;

    @Column(name = "amount")
    Double amount;

    @OneToOne
    OfferEntity offerEntity;

    @OneToMany(mappedBy = "payment_id", cascade = CascadeType.ALL)
    List<PaymentEntity> paymentEntity;

//    @ManyToOne
//    @JoinColumn(name = "client_id")
//    ClientAccountEntity clientAccountEntity;

}
