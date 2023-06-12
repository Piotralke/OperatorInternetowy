package pl.psk.upc.web.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class LoginRequestDto {

    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";

    @NotBlank(message = "Adres e-mail jest wymagany")
    @Email(message = "Niepoprawny adres e-mail")
    @JsonProperty(EMAIL)
    private String email;

    @NotBlank(message = "Hasło jest wymagane")
    @Size(min = 8, message = "Hasło musi mieć co najmniej 8 znaków")
    @JsonProperty(PASSWORD)
    private String password;

    public LoginRequestDto(@JsonProperty(EMAIL) String email, @JsonProperty(PASSWORD) String password) {
        this.email = email;
        this.password = password;
    }

}
