package pl.psk.upc.application.userproblem;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.UserProblemEntity;
import pl.psk.upc.infrastructure.enums.UserProblemStatusEnum;
import pl.psk.upc.infrastructure.repository.ClientRepository;
import pl.psk.upc.infrastructure.repository.UserProblemRepository;
import pl.psk.upc.tech.MethodArgumentValidator;
import pl.psk.upc.web.userproblem.*;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
class UserProblemServiceImpl implements UserProblemService {
    private final static String USER_NOT_FOUND_MESSAGE = "User not found";

    private final ClientRepository clientRepository;
    private final UserProblemRepository userProblemRepository;

    public UserProblemServiceImpl(ClientRepository clientRepository, UserProblemRepository userProblemRepository) {
        this.clientRepository = clientRepository;
        this.userProblemRepository = userProblemRepository;
    }

    @Override
    public UUID saveUserProblem(UserProblemInputDto userProblemInputDto) {
        MethodArgumentValidator.requiredNotNull(userProblemInputDto, "userProblemInputDto");
        ClientAccountEntity clientAccountEntity = clientRepository.findByEmail(userProblemInputDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));
        UserProblemEntity userProblem = UserProblemEntity.builder()
                .uuid(UUID.randomUUID())
                .description(userProblemInputDto.getDescription())
                .userProblemStartDate(ZonedDateTime.now(ZoneId.systemDefault()))
                .userProblemStatus(UserProblemStatusEnum.NOT_STARTED)
                .clientAccountEntity(clientAccountEntity)
                .build();

        return userProblemRepository.save(userProblem).getUuid();
    }

    @Override
    public UserProblemDtoWrapper getUserProblems(String email) {
        MethodArgumentValidator.requiredNotNullOrBlankString(email, "email");
        ClientAccountEntity clientAccountEntity = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));

        List<UserProblemEntity> userProblems = userProblemRepository.findByClientAccountEntity(clientAccountEntity);
        return UserProblemConverter.convertFrom(userProblems);
    }

    @Override
    public UserProblemDtoWrapper getAll() {
        return UserProblemConverter.convertFrom(userProblemRepository.findAll());
    }

    @Override
    public UserProblemDto getUserProblem(UUID uuid) {
        MethodArgumentValidator.requiredNotNull(uuid, "uuid");
        UserProblemEntity userProblem = userProblemRepository.findByUuid(uuid);
        return UserProblemConverter.convertFrom(userProblem);
    }

    @Override
    public UserProblemDto setUserProblemStatus(UserProblemSetStatusInputDto inputDto) {
        MethodArgumentValidator.requiredNotNull(inputDto, "inputDto");
        UserProblemEntity userProblem = userProblemRepository.findByUuid(inputDto.getUuid());
        userProblem.setUserProblemStatus(inputDto.getStatus());
        if (inputDto.getStatus().equals(UserProblemStatusEnum.END)) {
            userProblem.setUserProblemEndDate(ZonedDateTime.now(ZoneId.systemDefault()));
        }
        UserProblemEntity saved = userProblemRepository.save(userProblem);
        return UserProblemConverter.convertFrom(saved);
    }
}
