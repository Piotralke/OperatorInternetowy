package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Warehouses")
public class WarehouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "product_name")
    String productName;

    @Column(name = "quantity")
    Integer quantity;

    @OneToOne
    ProductEntity productEntity;

    @Builder
    public WarehouseEntity(Long id, UUID uuid, String productName, Integer quantity, ProductEntity productEntity) {
        this.id = id;
        this.uuid = uuid;
        this.productName = productName;
        this.quantity = quantity;
        this.productEntity = productEntity;
    }
}
