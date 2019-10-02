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
import ru.senla.dto.CredentialDto;
import ru.senla.service.CredentialService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)


public class CredentialServiceTest {
    private CredentialService credentialService;
    private static CredentialDto credentialDto;
    private static Long id;

    @Autowired
    public void setCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @BeforeClass
    public static void init() {

        credentialDto = new CredentialDto();
        credentialDto.setEmail("se@mail.ru");
        credentialDto.setLogin("Login");
        credentialDto.setPassword("mypassword");
        credentialDto.setRole("admin");
    }

    @Test
    public void aSave() {
        id = credentialService.saveCredential(credentialDto);
    }

    @Test
    public void getCredentialById() {
        CredentialDto credentialDtoFromDb = credentialService.getCredentialById(id);
        assertEquals("Login", credentialDtoFromDb.getLogin());
    }

    @Test
    public void updateAndDeleteCredential() {
        credentialDto.setCredentialId(id);
        credentialDto.setLogin("NotMyLogin");
        credentialService.updateCredential(credentialDto);
        credentialService.deleteCredential(id);
    }

    @Test
    public void getAllCredentials() {
        List<CredentialDto> credentialDtoList = credentialService.getAllCredentials();
        assertNotNull(credentialDtoList);
    }
}
