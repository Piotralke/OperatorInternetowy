package pl.psk.upc.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import pl.psk.upc.exception.NoAccessRightsException;
import pl.psk.upc.infrastructure.enums.UserRoleToPrefixEnum;
import pl.psk.upc.web.UpcRestPaths;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class AuthoritiesFilter extends BasicAuthenticationFilter {

    private String KEY = "secretsecretsecretsecretsecretsecretsecretsecretsecretsecret";
    private final JwtPropertiesConfig jwtPropertiesConfig;

    public AuthoritiesFilter(AuthenticationManager authenticationManager, JwtPropertiesConfig jwtPropertiesConfig) {
        super(authenticationManager);
        this.jwtPropertiesConfig = jwtPropertiesConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        if (!url.contains(UpcRestPaths.UPC_UNSECURED_PREFIX)) {
            String token = request.getHeader(HttpHeaders.AUTHORIZATION).replace("Bearer ", "");
            JwtParser jwtParser = Jwts.parserBuilder()
                    .setSigningKey(jwtPropertiesConfig.getSecretKey().getBytes())
                    .build();

            Claims claims = jwtParser.parseClaimsJws(token)
                    .getBody();
            String email = claims.get("sub").toString();
            String role = claims.get("role").toString();

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(email, null, Collections.singletonList(new SimpleGrantedAuthority(role)));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            if (!getRequiredRoleForUrl(url).contains(role)) {
                throw new NoAccessRightsException("No access rights");
            }
        }
        filterChain.doFilter(request, response);
    }

    private List<String> getRequiredRoleForUrl(String url) {
        if (url.contains(UserRoleToPrefixEnum.ADMIN_PREFIX.getUrlPrefix())) {
            return UserRoleToPrefixEnum.ADMIN_PREFIX.getRequiredRolesForPrefix();
        } else if (url.contains(UserRoleToPrefixEnum.WORKER_PREFIX.getUrlPrefix())) {
            return UserRoleToPrefixEnum.WORKER_PREFIX.getRequiredRolesForPrefix();
        } else {
            return UserRoleToPrefixEnum.USER_PREFIX.getRequiredRolesForPrefix();
        }
    }
}
