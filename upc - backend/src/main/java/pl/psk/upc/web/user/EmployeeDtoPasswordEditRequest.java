package pl.psk.upc.web.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class EmployeeDtoPasswordEditRequest {

    private final static String UUID = "uuid";
    private final static String PASSWORD = "password";

    @NotBlank
    @JsonProperty(UUID)
    java.util.UUID uuid;

    @NotBlank(message = "Hasło jest wymagane")
    @Size(min = 8, message = "Hasło musi mieć co najmniej 8 znaków")
    @JsonProperty(PASSWORD)
    String password;

    public EmployeeDtoPasswordEditRequest(@JsonProperty(UUID) java.util.UUID uuid, @JsonProperty(PASSWORD) String password) {
        this.uuid = uuid;
        this.password = password;
    }
}
