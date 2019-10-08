package springTest;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.senla.context.AppConfig;
import ru.senla.dao.entityDao.CredentialDao;
import ru.senla.dto.CredentialDto;
import ru.senla.dto.UserDto;
import ru.senla.entity.User;
import ru.senla.service.CredentialService;
import ru.senla.service.UserService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UserServiceTest {
    private static UserService userService;
    private static UserDto userDto;
    private static Long id;
    private static CredentialDto credentialDto;
    private static CredentialService credentialService;
    private static Long credentialId;

    @Autowired
    public void setCredentialService(CredentialService credentialService) {
        UserServiceTest.credentialService = credentialService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @BeforeClass
    public static void init() {
        userDto = new UserDto();
        userDto.setFirstName("Dmitry");
        userDto.setGender(true);
        userDto.setBirthDate(new Date());
        userDto.setUserRating(15);

        credentialDto = new CredentialDto();
        credentialDto.setEmail("rest@mail.ru");
        credentialDto.setLogin("Log2");
        credentialDto.setPassword("mypassword");
        credentialDto.setRole("user");
    }

    @Test
    public void aSave() {
        credentialId = credentialService.saveCredential(credentialDto);
        userDto.setCredentialId(credentialId);
        id = userService.saveUser(userDto);
    }

    @Test
    public void getUserById() {
        UserDto userDtoFromDb = userService.getUserById(id);
        assertEquals(userDto.getUserRating(), userDtoFromDb.getUserRating());
    }

    @Test
    public void updateAndDeleteUser() {
        userDto.setId(id);
        userDto.setUserRating(5);
        userService.updateUser(userDto);
        userService.deleteUser(id);
        credentialService.deleteCredential(credentialId);
    }

    @Test
    public void getAllUsers() {
        List<UserDto> userList = userService.getAllUsers();
        assertNotNull(userList);
    }
}
