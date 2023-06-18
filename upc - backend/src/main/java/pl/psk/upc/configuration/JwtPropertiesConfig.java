package pl.psk.upc.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class JwtPropertiesConfig {

    private final String secretKey;

    private final long tokenExpiration;

    public JwtPropertiesConfig(@Value("${application.security.jwt.secret-key}") String secretKey, @Value("${application.security.jwt.expiration}") long tokenExpiration) {
        this.secretKey = secretKey;
        this.tokenExpiration = tokenExpiration;
    }
}
