package pl.psk.upc.configuration;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.EmployeeEntity;
import pl.psk.upc.infrastructure.repository.ClientRepository;
import pl.psk.upc.infrastructure.repository.EmployeeRepository;

import java.util.Optional;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;

    public UserInfoUserDetailsService(ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<ClientAccountEntity> clientByEmail = clientRepository.findByEmail(email);
        if (clientByEmail.isPresent()) {
            return clientByEmail.map(UserInfoUserDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("user not found " + email));
        }

        Optional<EmployeeEntity> employeeByEmail = employeeRepository.findByEmail(email);
        if (employeeByEmail.isPresent()) {
            return employeeByEmail.map(UserInfoUserDetails::new)
                    .orElseThrow(() -> new UsernameNotFoundException("user not found " + email));
        }

        throw new UsernameNotFoundException("user not found " + email);
    }
}
