package pl.psk.upc.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonManagedReference
    @OneToMany(mappedBy = "contractEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<PaymentEntity> paymentEntities;

    @JsonBackReference
    @OneToOne(mappedBy = "contractEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    ServiceEntity serviceEntity;

}
