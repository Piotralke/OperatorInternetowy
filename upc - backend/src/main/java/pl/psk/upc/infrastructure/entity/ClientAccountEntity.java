package pl.psk.upc.infrastructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import pl.psk.upc.infrastructure.dto.Account;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Builder
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "balance")
    private double balance;

    @Column(name = "account_status")
    private String accountStatus;

    @Column(name = "roles")
    private String roles;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "nip")
    private String nip;

    @Column(name = "pesel")
    private String pesel;

    @Column(name = "is_business_client")
    private boolean isBusinessClient;

    @JsonBackReference
    @OneToMany(mappedBy = "order_id", cascade = CascadeType.ALL)
    List<OrderEntity> orderEntities;

    @OneToMany(mappedBy = "user_problem_id", cascade = CascadeType.ALL)
    List<UserProblemEntity> userProblems;

    @JsonManagedReference
    @OneToMany(mappedBy = "service_id", cascade = CascadeType.ALL)
    List<ServiceEntity> services;

    public UUID getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountStatus() {
        return this.accountStatus;
    }

    public String getRoles() {
        return roles;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

}





