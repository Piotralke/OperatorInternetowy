package pl.psk.upc.application.userproblem;

import pl.psk.upc.infrastructure.entity.UserProblemEntity;
import pl.psk.upc.web.userproblem.UserProblemDto;
import pl.psk.upc.web.userproblem.UserProblemDtoWrapper;

import java.util.List;

public class UserProblemConverter {

    public static UserProblemDto convertFrom(UserProblemEntity userProblem) {
        return UserProblemDto.builder()
                .uuid(userProblem.getUuid())
                .description(userProblem.getDescription())
                .userProblemStartDate(userProblem.getUserProblemStartDate())
                .userProblemEndDate(userProblem.getUserProblemEndDate())
                .userProblemStatus(userProblem.getUserProblemStatus())
                .build();
    }

    public static UserProblemDtoWrapper convertFrom(List<UserProblemEntity> userProblems) {
        if (userProblems == null || userProblems.isEmpty()) {
            return UserProblemDtoWrapper.builder().build();
        }

        List<UserProblemDto> convertedProblems = userProblems.stream()
                .map(UserProblemConverter::convertFrom)
                .toList();

        return UserProblemDtoWrapper.builder()
                .content(convertedProblems)
                .build();
    }

}
