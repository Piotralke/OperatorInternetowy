package pl.psk.upc.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.psk.upc.infrastructure.enums.UserProblemStatusEnum;

import java.time.LocalDate;
import java.time.ZonedDateTime;
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
    ZonedDateTime userProblemStartDate;

    @Column(name = "user_problem_end_date")
    ZonedDateTime userProblemEndDate;

    @Column(name = "user_problem_status")
    UserProblemStatusEnum userProblemStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    ClientAccountEntity clientAccountEntity;

    @Builder
    public UserProblemEntity(Long user_problem_id, UUID uuid, String description, ZonedDateTime userProblemStartDate, ZonedDateTime userProblemEndDate, UserProblemStatusEnum userProblemStatus, ClientAccountEntity clientAccountEntity) {
        this.user_problem_id = user_problem_id;
        this.uuid = uuid;
        this.description = description;
        this.userProblemStartDate = userProblemStartDate;
        this.userProblemEndDate = userProblemEndDate;
        this.userProblemStatus = userProblemStatus;
        this.clientAccountEntity = clientAccountEntity;
    }
}
