package pl.psk.upc.web.integration.userproblem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.psk.upc.infrastructure.enums.UserProblemStatusEnum;
import pl.psk.upc.web.UpcTest;
import pl.psk.upc.web.user.ClientDto;
import pl.psk.upc.web.user.UserController;
import pl.psk.upc.web.userproblem.UserProblemController;
import pl.psk.upc.web.userproblem.UserProblemDto;
import pl.psk.upc.web.userproblem.UserProblemDtoWrapper;
import pl.psk.upc.web.userproblem.UserProblemSetStatusInputDto;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserProblemControllerTest extends UpcTest {

    @Autowired
    UserProblemController userProblemController;

    @Autowired
    UserController userController;

    @Test
    @Transactional
    void saveUserProblem() {
        ClientDto userBeforeSaveProblems = userController.getUserByEmail(userProblemInputDto.getEmail());

        assertEquals(0, userBeforeSaveProblems.getUserProblems().size());

        UUID uuid = userProblemController.saveUserProblem(userProblemInputDto);
        UUID uuid1 = userProblemController.saveUserProblem(userProblemInputDto2);

        ClientDto userAfterSaveProblems = userController.getUserByEmail(userProblemInputDto.getEmail());

        assertEquals(2, userAfterSaveProblems.getUserProblems().size());

        UserProblemDto userProblem = userProblemController.getUserProblem(uuid);
        UserProblemDto userProblem1 = userProblemController.getUserProblem(uuid1);

        assertNotNull(userProblem.getUuid());
        assertNotNull(userProblem.getUserProblemStartDate());
        assertNull(userProblem.getUserProblemEndDate());
        assertEquals(userProblemInputDto.getDescription(), userProblem.getDescription());
        assertEquals(UserProblemStatusEnum.NOT_STARTED, userProblem.getUserProblemStatus());

        assertNotNull(userProblem1.getUuid());
        assertNotNull(userProblem1.getUserProblemStartDate());
        assertNull(userProblem1.getUserProblemEndDate());
        assertEquals(userProblemInputDto2.getDescription(), userProblem1.getDescription());
        assertEquals(UserProblemStatusEnum.NOT_STARTED, userProblem1.getUserProblemStatus());
    }

    @Test
    @Transactional
    void getUserProblemsWhenUserProblemsAreEmpty() {
        UserProblemDtoWrapper userProblemsBeforeSave = userProblemController.getUserProblems(userProblemInputDto.getEmail());

        assertEquals(0, userProblemsBeforeSave.getContent().size());
    }

    @Test
    @Transactional
    void getUserProblemsWhenUserProblemsAreNotEmpty() {
        UserProblemDtoWrapper userProblemsBeforeSave = userProblemController.getUserProblems(userProblemInputDto.getEmail());

        assertEquals(0, userProblemsBeforeSave.getContent().size());

        userProblemController.saveUserProblem(userProblemInputDto);
        userProblemController.saveUserProblem(userProblemInputDto2);

        UserProblemDtoWrapper userProblemsAfterSave = userProblemController.getUserProblems(userProblemInputDto.getEmail());

        assertEquals(2, userProblemsAfterSave.getContent().size());
    }

    @Test
    @Transactional
    void getUserProblem() {
        UUID uuid = userProblemController.saveUserProblem(userProblemInputDto);

        UserProblemDto userProblem = userProblemController.getUserProblem(uuid);

        assertNotNull(userProblem.getUuid());
        assertNotNull(userProblem.getUserProblemStartDate());
        assertNull(userProblem.getUserProblemEndDate());
        assertEquals(userProblemInputDto.getDescription(), userProblem.getDescription());
        assertEquals(UserProblemStatusEnum.NOT_STARTED, userProblem.getUserProblemStatus());
    }

    @Test
    @Transactional
    void setUserProblemStatusToInProgress() {
        UUID uuid = userProblemController.saveUserProblem(userProblemInputDto);
        UserProblemSetStatusInputDto inputDto = UserProblemSetStatusInputDto.builder()
                .uuid(uuid)
                .status(UserProblemStatusEnum.IN_PROGRESS)
                .build();
        userProblemController.setUserProblemStatus(inputDto);

        UserProblemDto userProblem = userProblemController.getUserProblem(uuid);

        assertNotNull(userProblem.getUuid());
        assertNotNull(userProblem.getUserProblemStartDate());
        assertNull(userProblem.getUserProblemEndDate());
        assertEquals(userProblemInputDto.getDescription(), userProblem.getDescription());
        assertEquals(UserProblemStatusEnum.IN_PROGRESS, userProblem.getUserProblemStatus());
    }

    @Test
    @Transactional
    void setUserProblemStatusToEnd() {
        UUID uuid = userProblemController.saveUserProblem(userProblemInputDto);
        UserProblemSetStatusInputDto inputDto = UserProblemSetStatusInputDto.builder()
                .uuid(uuid)
                .status(UserProblemStatusEnum.END)
                .build();
        userProblemController.setUserProblemStatus(inputDto);

        UserProblemDto userProblem = userProblemController.getUserProblem(uuid);

        assertNotNull(userProblem.getUuid());
        assertNotNull(userProblem.getUserProblemStartDate());
        assertNotNull(userProblem.getUserProblemEndDate());
        assertEquals(userProblemInputDto.getDescription(), userProblem.getDescription());
        assertEquals(UserProblemStatusEnum.END, userProblem.getUserProblemStatus());
    }

    @Test
    @Transactional
    void getALLProblemsWhenAreEmpty() {
        UserProblemDtoWrapper allProblems = userProblemController.getALLProblems();
        assertEquals(0, allProblems.getContent().size());
    }
    @Test
    @Transactional
    void getALLProblemsWhenAreNotEmpty() {
        userProblemController.saveUserProblem(userProblemInputDto);
        UserProblemDtoWrapper allProblems = userProblemController.getALLProblems();
        assertEquals(1, allProblems.getContent().size());
    }

}