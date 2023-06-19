package pl.psk.upc.application.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.psk.upc.configuration.JwtPropertiesConfig;
import pl.psk.upc.configuration.UserInfoUserDetailsService;
import pl.psk.upc.web.login.LoginRequestDto;

import java.util.Date;

@Service
class LoginServiceImpl implements LoginService {

    private final UserInfoUserDetailsService userDetailsService;
    private final JwtPropertiesConfig jwtPropertiesConfig;

    public LoginServiceImpl(UserInfoUserDetailsService userDetailsService, JwtPropertiesConfig jwtPropertiesConfig) {
        this.userDetailsService = userDetailsService;
        this.jwtPropertiesConfig = jwtPropertiesConfig;
    }

    @Override
    public String login(LoginRequestDto loginRequestDto) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getEmail());
        long currentTimeMillis = System.currentTimeMillis();
        return JWT.create()
                .withSubject(userDetails.getUsername())
                .withIssuer("self")
                .withIssuedAt(new Date(currentTimeMillis))
                .withExpiresAt(new Date(currentTimeMillis + jwtPropertiesConfig.getTokenExpiration()))
                .withClaim("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList().get(0))
                .sign(Algorithm.HMAC256(jwtPropertiesConfig.getSecretKey()));
    }
}
