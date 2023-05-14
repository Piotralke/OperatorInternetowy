//package pl.psk.upc.infrastructure.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.UUID;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name = "accounts") @Builder
//public class AccountEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "uuid")
//    private UUID uuid;
//
//    @Column(name = "email", unique = true)
//    private String email;
//
//    @Column(name = "password")
//    private String password;
//
//    @Column(name = "balance")
//    private double balance;
//
//    @Column(name = "account_status")
//    private String accountStatus;
//
//    @Column(name = "roles")
//    private String roles;
//
//}
