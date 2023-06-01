package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Value;

import java.util.UUID;

@Value
@Entity
@Table(name = "services")
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long service_id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "name")
    String name;

    @Column(name = "description", nullable = false)
    String description;

    @Column(name = "price")
    double price;

}
