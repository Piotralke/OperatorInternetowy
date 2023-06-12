package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.psk.upc.infrastructure.dto.Account;

import java.util.UUID;

@Entity
@Table(name = "employees") @Builder @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity implements Account {

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

    @Column(name = "account_status")
    private String accountStatus;

    @Column(name = "roles")
    private String roles;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "workplace")
    private String workplace;

    @Column(name = "salary")
    private double salary;

    @Column(name = "contract_form")
    private ContractForm contractForm;

    @Column(name = "nip")
    private String nip;

    @Column(name = "pesel")
    private String pesel;

    public Long getId() {
        return id;
    }

    @Override
    public UUID getUuid() {
        return uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    @Override
    public String getRoles() {
        return roles;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getWorkplace() {
        return workplace;
    }

    public double getSalary() {
        return salary;
    }

    public ContractForm getContractForm() {
        return contractForm;
    }

    public String getNip() {
        return nip;
    }

    public String getPesel() {
        return pesel;
    }
}