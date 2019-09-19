package springTest;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.senla.context.AppConfig;
import ru.senla.entity.User;
import ru.senla.service.UserService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class UserServiceTest {
    private static UserService userService;
    private static User user;
    private static Long id;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @BeforeClass
    public static void init() {
        user = new User("Sergei", true, new Date(), 1);
    }

    @Test
    public void aSave() {
        id = userService.saveUser(user);
    }

    @Test
    public void getUserById() {
        User userFromDb = userService.getUserById(id);
        assertEquals(1, userFromDb.getUserRating());
    }

    @Test
    public void updateAndDeleteUser() {
        user.setId(id);
        user.setUserRating(5);
        userService.updateUser(user);
        userService.deleteUser(user);
    }

    @Test
    public void getAllUsers() {
        List<User> userList = userService.getAllUsers();
        assertNotNull(userList);
    }
}
