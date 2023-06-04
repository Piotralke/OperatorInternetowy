package pl.psk.upc.application.userproblem;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.UserProblemEntity;
import pl.psk.upc.infrastructure.enums.UserProblemStatusEnum;
import pl.psk.upc.infrastructure.repository.ClientRepository;
import pl.psk.upc.infrastructure.repository.UserProblemRepository;
import pl.psk.upc.web.userproblem.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class UserProblemServiceImpl implements UserProblemService {

    private final ClientRepository clientRepository;
    private final UserProblemRepository userProblemRepository;

    public UserProblemServiceImpl(ClientRepository clientRepository, UserProblemRepository userProblemRepository) {
        this.clientRepository = clientRepository;
        this.userProblemRepository = userProblemRepository;
    }

    @Override
    public UUID saveUserProblem(UserProblemInputDto userProblemInputDto) {
        ClientAccountEntity clientAccountEntity = clientRepository.findByEmail(userProblemInputDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        UserProblemEntity userProblem = UserProblemEntity.builder()
                .uuid(UUID.randomUUID())
                .description(userProblemInputDto.getDescription())
                .userProblemStartDate(LocalDate.now())
                .userProblemEndDate(LocalDate.now().plusDays(2L))
                .userProblemStatus(UserProblemStatusEnum.NOT_STARTED)
                .clientAccountEntity(clientAccountEntity)
                .build();

        return userProblemRepository.save(userProblem).getUuid();
    }

    @Override
    public UserProblemDtoWrapper getUserProblems(String email) {
        ClientAccountEntity clientAccountEntity = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        List<UserProblemEntity> userProblems = userProblemRepository.findByClientAccountEntity(clientAccountEntity);
        return UserProblemConverter.convertFrom(userProblems);
    }

    @Override
    public UserProblemDto getUserProblem(UUID uuid) {
        UserProblemEntity userProblem = userProblemRepository.findByUuid(uuid);
        return UserProblemConverter.convertFrom(userProblem);
    }

    @Override
    public UserProblemDto getUserProblem(UserProblemSetStatusInputDto inputDto) {
        UserProblemEntity userProblem = userProblemRepository.findByUuid(inputDto.getUuid());
        userProblem.setUserProblemStatus(inputDto.getStatus());
        UserProblemEntity saved = userProblemRepository.save(userProblem);
        return UserProblemConverter.convertFrom(saved);
    }
}
