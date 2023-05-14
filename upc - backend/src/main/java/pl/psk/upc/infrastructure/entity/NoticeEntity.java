package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Value;

import java.time.LocalDate;
import java.util.UUID;

@Value
@Entity
@Table(name = "notices")
public class NoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "notice_date")
    LocalDate noticeDate;

    @Column(name = "description")
    String description;

    @ManyToOne
    @JoinColumn(name = "client_id")
    ClientAccountEntity clientAccountEntity;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    EmployeeEntity employeeEntity;
}
