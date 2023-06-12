package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.psk.upc.infrastructure.enums.OfferType;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "offers")
public class OfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "name")
    String name;

    @Column(name = "description", nullable = false, length = 1000000000)
    String description;

    @Column(name = "price")
    double price;

    @OneToOne
    ProductEntity productEntity;

    @Column(name = "with_device")
    boolean withDevice;

    @Column(name = "offer_type")
    OfferType offerType;

    @Column(name = "is_archival")
    boolean isArchival;

    @Builder
    public OfferEntity(Long id, UUID uuid, String name, String description, double price, ProductEntity productEntity, boolean withDevice, OfferType offerType, boolean isArchival) {
        this.id = id;
        this.uuid = uuid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productEntity = productEntity;
        this.withDevice = withDevice;
        this.offerType = offerType;
        this.isArchival = isArchival;
    }
}
