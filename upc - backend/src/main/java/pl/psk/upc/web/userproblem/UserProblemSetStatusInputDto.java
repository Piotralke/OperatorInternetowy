package pl.psk.upc.web.userproblem;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import pl.psk.upc.infrastructure.enums.UserProblemStatusEnum;

import java.util.UUID;

@Value
public class UserProblemSetStatusInputDto {

    private final static String UUID = "uuid";
    private final static String STATUS = "status";

    @NotNull
    @JsonProperty(UUID)
    UUID uuid;

    @NotNull
    @JsonProperty(STATUS)
    UserProblemStatusEnum status;

    public UserProblemSetStatusInputDto(@JsonProperty(UUID) UUID uuid, @JsonProperty(STATUS) UserProblemStatusEnum status) {
        this.uuid = uuid;
        this.status = status;
    }
}
