package pl.psk.upc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import pl.psk.upc.infrastructure.repository.ClientRepository;
import pl.psk.upc.infrastructure.repository.EmployeeRepository;
import pl.psk.upc.web.UpcRestPaths;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final ClientRepository clientRepository;
    private final EmployeeRepository employeeRepository;
    private final AuthenticationConfiguration authenticationConfiguration;

    public SecurityConfig(ClientRepository clientRepository, EmployeeRepository employeeRepository, AuthenticationConfiguration authenticationConfiguration) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    @Bean
    public PasswordEncoder getBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:5173");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedHeader("*");

        http.cors().configurationSource(request -> corsConfiguration);
        http.csrf().disable()
                .authorizeHttpRequests((requests) -> {
                            try {
                                requests
                                        .requestMatchers(UpcRestPaths.UPC_SECURED_PREFIX + "/*").hasAnyRole( "WORKER", "ADMIN")
                                        .requestMatchers(UpcRestPaths.UPC_UNSECURED_PREFIX + "/*").permitAll()
                                        .anyRequest().authenticated().and()
                                        .addFilter(new AuthoritiesFilter(authenticationConfiguration.getAuthenticationManager()))
                                        .httpBasic().disable().sessionManagement().disable();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }

                )
                .httpBasic().disable();

        return http.build();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//            throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http)
//            throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .userDetailsService(new UserInfoUserDetailsService(clientRepository, employeeRepository))
//                .passwordEncoder(bCryptPasswordEncoder)
//                .and()
//                .build();
//    }

}
