package pl.psk.upc.web.userproblem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.infrastructure.enums.UserProblemStatusEnum;

import java.time.LocalDate;
import java.util.UUID;

@Value
public class UserProblemDto {

    private final static String UUID = "uuid";
    private final static String DESCRIPTION = "description";
    private final static String PROBLEM_START_DATE = "userProblemStartDate";
    private final static String PROBLEM_END_DATE = "userProblemEndDate";
    private final static String PROBLEM_STATUS = "userProblemStatus";

    @JsonProperty(UUID)
    java.util.UUID uuid;

    @JsonProperty(DESCRIPTION)
    String description;

    @JsonProperty(PROBLEM_START_DATE)
    LocalDate userProblemStartDate;

    @JsonProperty(PROBLEM_END_DATE)
    LocalDate userProblemEndDate;

    @JsonProperty(PROBLEM_STATUS)
    UserProblemStatusEnum userProblemStatus;

    @Builder
    public UserProblemDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(DESCRIPTION) String description, @JsonProperty(PROBLEM_START_DATE) LocalDate userProblemStartDate,
                          @JsonProperty(PROBLEM_END_DATE) LocalDate userProblemEndDate, @JsonProperty(PROBLEM_STATUS) UserProblemStatusEnum userProblemStatus) {
        this.uuid = uuid;
        this.description = description;
        this.userProblemStartDate = userProblemStartDate;
        this.userProblemEndDate = userProblemEndDate;
        this.userProblemStatus = userProblemStatus;
    }
}
