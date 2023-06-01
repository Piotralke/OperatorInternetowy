package pl.psk.upc.infrastructure.entity;//package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Value
@Entity
@Table(name = "contracts")
public class ContractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long contract_id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date")
    LocalDate endDate;

    @Column(name = "amount")
    BigDecimal amount;

    @Column(name = "description", nullable = false)
    String description;

    @OneToOne
    OfferEntity offerEntity;

    @ManyToOne
    @JoinColumn(name = "client_id")
    ClientAccountEntity clientAccountEntity;

}
