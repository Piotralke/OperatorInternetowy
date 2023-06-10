package pl.psk.upc.application.userproblem;

import pl.psk.upc.web.userproblem.UserProblemDto;
import pl.psk.upc.web.userproblem.UserProblemDtoWrapper;
import pl.psk.upc.web.userproblem.UserProblemInputDto;
import pl.psk.upc.web.userproblem.UserProblemSetStatusInputDto;

import java.util.UUID;

public interface UserProblemService {
    UUID saveUserProblem(UserProblemInputDto userProblemInputDto);
    UserProblemDtoWrapper getUserProblems(String email);
    UserProblemDtoWrapper getAll();
    UserProblemDto getUserProblem(UUID uuid);
    UserProblemDto setUserProblemStatus(UserProblemSetStatusInputDto inputDto);
}
