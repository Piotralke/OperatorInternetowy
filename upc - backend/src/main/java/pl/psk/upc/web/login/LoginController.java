package pl.psk.upc.web.login;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.psk.upc.configuration.UserInfoUserDetailsService;
import pl.psk.upc.web.UpcRestPaths;

import java.util.stream.Collectors;

@RestController
public class LoginController {

    private final UserInfoUserDetailsService userDetailsService;
    private String KEY = "secretkey123";

    public LoginController(UserInfoUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    //TODO przekazać credentiale do metody autoryzacyjnej -> teraz można podać puste hasło i przejdzie
    @PostMapping(UpcRestPaths.LOGIN)
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequestDto.getEmail());
        Authentication authenticate = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        if (authenticate.isAuthenticated()) {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            String token = JWT.create()
                    .withSubject(userDetails.getUsername())
                    .withIssuer("Eminem")
                    .withClaim("roles", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(algorithm);

            return token;
        }
        throw new UsernameNotFoundException("User not found!");
    }

}
