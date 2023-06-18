package pl.psk.upc.web.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.psk.upc.configuration.UserInfoUserDetailsService;
import pl.psk.upc.web.UpcRestPaths;

import java.util.Date;
import java.util.stream.Collectors;

@RestController
public class LoginController {
    long twentyFourHoursInMillis = 24 * 60 * 60 * 1000;

    private final UserInfoUserDetailsService userDetailsService;
    private String KEY = "secretsecretsecretsecretsecretsecretsecretsecretsecretsecret";

    public LoginController(UserInfoUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(UpcRestPaths.LOGIN)
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getEmail());
        Algorithm algorithm = Algorithm.HMAC256(KEY);
        long currentTimeMillis = System.currentTimeMillis();
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer("self")
                .withIssuedAt(new Date(currentTimeMillis))
                .withExpiresAt(new Date(currentTimeMillis + twentyFourHoursInMillis))
                .withClaim("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().get(0))
                .sign(algorithm);
    }

}
