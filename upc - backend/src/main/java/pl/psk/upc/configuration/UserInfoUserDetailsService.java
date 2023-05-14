package pl.psk.upc.configuration;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.dto.RoleEnum;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.EmployeeEntity;
import pl.psk.upc.infrastructure.repository.ClientRepository;
import pl.psk.upc.infrastructure.repository.EmployeeRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserInfoUserDetailsService implements UserDetailsService {

    private final ClientRepository clientRepository;
    private EmployeeRepository employeeRepository;

    public UserInfoUserDetailsService(ClientRepository clientRepository, EmployeeRepository employeeRepository) {
        this.clientRepository = clientRepository;
        this.employeeRepository = employeeRepository;
    }


//    public UserDetails loadUserByUsername(String email, LoginProvider provider) throws UsernameNotFoundException {
//        if (provider.equals(LoginProvider.GOOGLE)) {
//            Optional<ClientAccountEntity> clientByEmail = clientRepository.findByEmail(email);
//            if (clientByEmail.isPresent()) {
//                return clientByEmail.map(UserInfoUserDetails::new)
//                        .orElseThrow(() -> new UsernameNotFoundException("user not found " + email));
//            }
//            ClientAccountEntity newAccountEntity = ClientAccountEntity.builder()
//                    .uuid(UUID.randomUUID())
//                    .email(email)
//                    .balance(0L)
//                    .accountStatus("new")
//                    .roles(RoleEnum.USER.name())
//                    .isBusinessClient(false)
//                    .build();
//
//            clientRepository.save(newAccountEntity);
//            Optional<ClientAccountEntity> newClientByEmail = clientRepository.findByEmail(email);
//            if (newClientByEmail.isPresent()) {
//                return newClientByEmail.map(UserInfoUserDetails::new)
//                        .orElseThrow(() -> new UsernameNotFoundException("user not found " + email));
//            }
//        }
//
//        Optional<ClientAccountEntity> clientByEmail = clientRepository.findByEmail(email);
//        if (clientByEmail.isPresent()) {
//            return clientByEmail.map(UserInfoUserDetails::new)
//                    .orElseThrow(() -> new UsernameNotFoundException("user not found " + email));
//        }
//
//
//        Optional<EmployeeEntity> employeeByEmail = employeeRepository.findByEmail(email);
//        if (employeeByEmail.isPresent()) {
//            return employeeByEmail.map(UserInfoUserDetails::new)
//                    .orElseThrow(() -> new UsernameNotFoundException("user not found " + email));
//        }
//
//
//        throw new UsernameNotFoundException("user not found " + email);
//    }

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
