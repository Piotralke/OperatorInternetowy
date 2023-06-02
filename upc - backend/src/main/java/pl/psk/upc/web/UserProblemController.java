package pl.psk.upc.web;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import pl.psk.upc.infrastructure.entity.ClientAccountEntity;
import pl.psk.upc.infrastructure.entity.UserProblemEntity;
import pl.psk.upc.infrastructure.repository.ClientRepository;
import pl.psk.upc.infrastructure.repository.UserProblemRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
public class UserProblemController {

    private final ClientRepository clientRepository;
    private final UserProblemRepository userProblemRepository;

    public UserProblemController(ClientRepository clientRepository, UserProblemRepository userProblemRepository) {
        this.clientRepository = clientRepository;
        this.userProblemRepository = userProblemRepository;
    }

    @PostMapping(UpcRestPaths.SAVE_USER_USER_PROBLEM)
    public UUID saveUserProblem(@RequestBody UserProblemInputDto userProblemInputDto) {
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

    @GetMapping(UpcRestPaths.GET_USER_PROBLEMS)
    public List<UserProblemEntity> getUserProblems(@RequestParam String email) {
        ClientAccountEntity clientAccountEntity = clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

        return userProblemRepository.findByClientAccountEntity(clientAccountEntity);
    }

    @GetMapping(UpcRestPaths.GET_USER_PROBLEM)
    public UserProblemEntity getUserProblem(@PathVariable(value = "uuid") UUID uuid) {
        return userProblemRepository.findByUuid(uuid);
    }

    @GetMapping(UpcRestPaths.SET_USER_PROBLEM_STATUS)
    public UserProblemEntity getUserProblem(@RequestBody UserProblemSetStatusInputDto inputDto) {
        UserProblemEntity byUuid = userProblemRepository.findByUuid(inputDto.getUuid());
        byUuid.setUserProblemStatus(inputDto.getStatus());
        return userProblemRepository.save(byUuid);
    }

}
