package pl.psk.upc.web;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.util.UUID;

@Value
public class UserProblemSetStatusInputDto {

    private final static String UUID = "uuid";
    private final static String STATUS = "status";

    @JsonProperty(UUID)
    private final UUID uuid;

    @JsonProperty(STATUS)
    private final UserProblemStatusEnum status;

    public UserProblemSetStatusInputDto(@JsonProperty(UUID) UUID uuid, @JsonProperty(STATUS) UserProblemStatusEnum status) {
        this.uuid = uuid;
        this.status = status;
    }
}
