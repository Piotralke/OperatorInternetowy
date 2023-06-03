package pl.psk.upc.web.login;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class LoginRequestDto {

    private final static String EMAIL = "email";
    private final static String PASSWORD = "password";

    @JsonProperty(EMAIL)
    private String email;

    @JsonProperty(PASSWORD)
    private String password;

    public LoginRequestDto(@JsonProperty(EMAIL) String email, @JsonProperty(PASSWORD) String password) {
        this.email = email;
        this.password = password;
    }

}
