package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "contracts")
@Builder
@Setter
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

    @ManyToOne
    @JoinColumn(name = "client_id")
    ClientAccountEntity clientAccountEntity;

}
