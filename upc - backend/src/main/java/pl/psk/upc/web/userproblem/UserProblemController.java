package pl.psk.upc.web.userproblem;

import org.springframework.web.bind.annotation.*;
import pl.psk.upc.application.userproblem.UserProblemService;
import pl.psk.upc.web.UpcRestPaths;

import java.util.UUID;

@RestController
public class UserProblemController {

    private final UserProblemService userProblemService;

    public UserProblemController(UserProblemService userProblemService) {
        this.userProblemService = userProblemService;
    }

    @PostMapping(UpcRestPaths.SAVE_USER_USER_PROBLEM)
    public UUID saveUserProblem(@RequestBody UserProblemInputDto userProblemInputDto) {
        return userProblemService.saveUserProblem(userProblemInputDto);
    }

    @GetMapping(UpcRestPaths.GET_USER_PROBLEMS)
    public UserProblemDtoWrapper getUserProblems(@RequestParam String email) {
        return userProblemService.getUserProblems(email);
    }

    @GetMapping(UpcRestPaths.GET_USER_PROBLEM)
    public UserProblemDto getUserProblem(@PathVariable(value = "uuid") UUID uuid) {
        return userProblemService.getUserProblem(uuid);
    }

    @PutMapping(UpcRestPaths.SET_USER_PROBLEM_STATUS)
    public UserProblemDto getUserProblem(@RequestBody UserProblemSetStatusInputDto inputDto) {
        return userProblemService.getUserProblem(inputDto);
    }

}
