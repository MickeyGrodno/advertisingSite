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
import ru.senla.entity.Credential;
import ru.senla.entity.User;
import ru.senla.service.CredentialService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CredentialServiceTest {
    private static CredentialService credentialService;
    private static Credential credential;
    private static Long id;

    @Autowired
    public void setCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @BeforeClass
    public static void  init() {
        User user = new User("Sergei", true, new Date(), 1);
        credential = new Credential();
        credential.setEmail("serg@mail.ru");
        credential.setLogin("MyLogin");
        credential.setPassword("mypassword");
        credential.setRole("admin");
        credential.setUser(user);
    }

    @Test
    public void aSave() {
        id = credentialService.saveCredential(credential);
    }

    @Test
    public void getCredentialById() {
        Credential credentialFromDb = credentialService.getCredentialById(id);
        assertEquals("MyLogin", credentialFromDb.getLogin());
    }

    @Test
    public void updateAndDeleteCredential() {
        credential.setCredentialId(id);
        credential.setLogin("NotMyLogin");
        credentialService.updateCredential(credential);
        credentialService.deleteCredential(credential);
    }

    @Test
    public void getAllCredentials() {
        List<Credential> credentialList = credentialService.getAllCredentials();
        assertNotNull(credentialList);
    }
}
