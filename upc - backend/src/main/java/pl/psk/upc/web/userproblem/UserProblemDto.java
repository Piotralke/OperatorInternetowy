package pl.psk.upc.web.userproblem;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import pl.psk.upc.infrastructure.enums.UserProblemStatusEnum;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

@Value
public class UserProblemDto {

    private final static String UUID = "uuid";
    private final static String DESCRIPTION = "description";
    private final static String PROBLEM_START_DATE = "userProblemStartDate";
    private final static String PROBLEM_END_DATE = "userProblemEndDate";
    private final static String PROBLEM_STATUS = "userProblemStatus";
    private final static String USER_FIRST_NAME = "userFirstName";
    private final static String USER_LAST_NAME = "userLastName";
    private final static String USER_UUID = "userUuid";

    @JsonProperty(UUID)
    java.util.UUID uuid;

    @JsonProperty(DESCRIPTION)
    String description;

    @JsonProperty(PROBLEM_START_DATE)
    ZonedDateTime userProblemStartDate;

    @JsonProperty(PROBLEM_END_DATE)
    ZonedDateTime userProblemEndDate;

    @JsonProperty(PROBLEM_STATUS)
    UserProblemStatusEnum userProblemStatus;

    @JsonProperty(USER_UUID)
    java.util.UUID userUuid;

    @JsonProperty(USER_FIRST_NAME)
    String userFirstName;

    @JsonProperty(USER_LAST_NAME)
    String userLastName;

    @Builder(toBuilder = true)
    public UserProblemDto(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(DESCRIPTION) String description, @JsonProperty(PROBLEM_START_DATE) ZonedDateTime userProblemStartDate,
                          @JsonProperty(PROBLEM_END_DATE) ZonedDateTime userProblemEndDate, @JsonProperty(PROBLEM_STATUS) UserProblemStatusEnum userProblemStatus,
                          @JsonProperty(USER_UUID) java.util.UUID userUuid, @JsonProperty(USER_FIRST_NAME) String userFirstName, @JsonProperty(USER_LAST_NAME) String userLastName) {
        this.uuid = uuid;
        this.description = description;
        this.userProblemStartDate = userProblemStartDate;
        this.userProblemEndDate = userProblemEndDate;
        this.userProblemStatus = userProblemStatus;
        this.userUuid = userUuid;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
    }
}
