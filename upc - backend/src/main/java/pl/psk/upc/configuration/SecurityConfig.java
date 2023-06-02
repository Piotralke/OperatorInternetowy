package pl.psk.upc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.psk.upc.web.UpcRestPaths;

@Configuration
public class SecurityConfig {

    private final UserInfoUserDetailsService userInfoUserDetailsService;
    private final JwtTokenFilter jwtTokenFilter;

    public SecurityConfig(UserInfoUserDetailsService userInfoUserDetailsService, JwtTokenFilter jwtTokenFilter) {
        this.userInfoUserDetailsService = userInfoUserDetailsService;
        this.jwtTokenFilter = jwtTokenFilter;
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return userInfoUserDetailsService;
    }

    @Bean
    public PasswordEncoder getBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authorizationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().and().cors().disable()
                .authorizeHttpRequests()
                .requestMatchers(UpcRestPaths.UPC_UNSECURED_PREFIX + "/**").permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}

