package pl.psk.upc.web.userproblem;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;

@Value
public class UserProblemInputDto {

    private final static String DESCRIPTION = "description";
    private final static String EMAIL = "email";

    @NotBlank
    @JsonProperty(DESCRIPTION)
    String description;

    @NotBlank(message = "Adres e-mail jest wymagany")
    @Email(message = "Niepoprawny adres e-mail")
    @JsonProperty(EMAIL)
    String email;

    @Builder
    public UserProblemInputDto(@JsonProperty(DESCRIPTION) String description, @JsonProperty(EMAIL) String email) {
        this.description = description;
        this.email = email;
    }
}
