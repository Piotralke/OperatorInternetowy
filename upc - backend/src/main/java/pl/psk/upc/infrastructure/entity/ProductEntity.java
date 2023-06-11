package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor @Getter @Setter
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long product_id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "name")
    String name;

    @Column(name = "price")
    Double price;

    @Column(name = "description", nullable = false,length = 1000000000)
    String description;

    @Column(name = "product_type")
    @Enumerated(EnumType.STRING)
    ProductType productType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    OrderEntity orderEntity;


    @Builder
    public ProductEntity(Long product_id, UUID uuid, String name, Double price, String description, ProductType productType) {
        this.product_id = product_id;
        this.uuid = uuid;
        this.name = name;
        this.price = price;
        this.description = description;
        this.productType = productType;
    }
}
