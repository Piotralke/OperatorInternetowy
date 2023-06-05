package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.psk.upc.infrastructure.enums.UserProblemStatusEnum;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_problems")
public class UserProblemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long user_problem_id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "description", length = 1000000000)
    String description;

    @Column(name = "user_problem_start_date")
    LocalDate userProblemStartDate;

    @Column(name = "user_problem_end_date")
    LocalDate userProblemEndDate;

    @Column(name = "user_problem_status")
    UserProblemStatusEnum userProblemStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    ClientAccountEntity clientAccountEntity;

    @Builder
    public UserProblemEntity(Long user_problem_id, UUID uuid, String description, LocalDate userProblemStartDate, LocalDate userProblemEndDate, UserProblemStatusEnum userProblemStatus, ClientAccountEntity clientAccountEntity) {
        this.user_problem_id = user_problem_id;
        this.uuid = uuid;
        this.description = description;
        this.userProblemStartDate = userProblemStartDate;
        this.userProblemEndDate = userProblemEndDate;
        this.userProblemStatus = userProblemStatus;
        this.clientAccountEntity = clientAccountEntity;
    }
}
