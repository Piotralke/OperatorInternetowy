package pl.psk.upc.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.psk.upc.exception.NoAccessRightsException;
import pl.psk.upc.web.UpcRestPaths;

import java.io.IOException;
import java.util.List;

@Component
public class AuthoritiesFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURL().toString();
        if (!url.contains(UpcRestPaths.UPC_UNSECURED_PREFIX)) {
            List<? extends GrantedAuthority> list = SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getAuthorities()
                    .stream().toList();
            if (!getRequiredRoleForUrl(url).contains(list.get(0).getAuthority())) {
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
