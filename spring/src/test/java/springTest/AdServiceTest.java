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
import ru.senla.dto.AdDto;
import ru.senla.dto.AdTypeDto;
import ru.senla.dto.CredentialDto;
import ru.senla.dto.UserDto;
import ru.senla.entity.Ad;
import ru.senla.entity.AdType;
import ru.senla.entity.Credential;
import ru.senla.entity.User;
import ru.senla.service.AdService;
import ru.senla.service.AdTypeService;
import ru.senla.service.CredentialService;
import ru.senla.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdServiceTest {
    @Autowired
    private AdService adService;
    @Autowired
    private AdTypeService adTypeService;
    @Autowired
    private UserService userService;
    @Autowired
    private CredentialService credentialService;

    private static AdDto adDto;
    private static Long adId;
    private static Long userId;
    private static Long credentialId;
    private static AdTypeDto adTypeDto;
    private static UserDto userDto;
    private static CredentialDto credentialDto;
    private static Long adTypeId;

    @BeforeClass
    public static void createTestData() {

        userDto = new UserDto();
        adDto = new AdDto();
        userDto.setFirstName("Dmitry");
        userDto.setGender(true);
        userDto.setBirthDate(new Date());
        userDto.setUserRating(15);

        adTypeDto = new AdTypeDto();
        adTypeDto.setBuyOrSale(true);
        adTypeDto.setCategory("Auto");
        adTypeDto.setClassification("Mercedes");

        credentialDto = new CredentialDto();
        credentialDto.setEmail("serg@mail.ru");
        credentialDto.setLogin("MyLogin");
        credentialDto.setPassword("mypassword");
        credentialDto.setRole("admin");

        adDto.setAdTypeDto(adTypeDto);
        adDto.setAdMessage("Test adDto");
        adDto.setAdDate(new Date());
        adDto.setAdTopDate(new Date());
    }

    @Test
    public void test1Save() {
        credentialId = credentialService.saveCredential(credentialDto);
        userDto.setCredentialId(credentialId);
        adDto.setUserDto(userDto);
        userId = userService.saveUser(userDto);
        userDto.setId(userId);
        adDto.setUserDto(userDto);
        adTypeId = adTypeService.saveAdType(adTypeDto);
        adTypeDto.setId(adTypeId);
        adDto.setAdTypeDto(adTypeDto);
        adId = adService.saveAd(adDto);
    }

    @Test
    public void test2GetByUserId(){

        List<AdDto> adList = adService.getAllAds();
        userId = adList.get(0).getUserDto().getId();
        List<AdDto> ads = adService.getAdsByUserId(userId);
        assertTrue(ads.size() > 0);
    }
    @Test
    public void test3searchByAdMessageText() {
        List<AdDto> adDtoList = adService.searchByAdMessageText("Test adDto");
        assertTrue(adDtoList.size() > 0);
    }

    @Test
    public void test4searchAdByAdType() {

        List<AdTypeDto> adTypeList = adTypeService.getAllAdTypes();
        List<AdDto> adDtoList = adService.searchAdByAdType(adTypeList.get(0).getId());
        assertTrue(adDtoList.size() > 0);
    }

    @Test
    public void test5searchAdByUserLogin() {
        List<AdDto> adDtoList = adService.searchAdByUserLogin("MyLogin");

    }
    @Test
    public void test6getAdById() {
        AdDto adDto = adService.getAdById(adId);
        adTypeId = adDto.getAdTypeDto().getId();
        assertTrue(adDto.getAdMessage().equals("Test adDto"));
    }

    @Test
    public void test8updateAndDeleteAd() {
        adDto.setId(adId);
        adDto.setAdMessage("new test Message");
        adService.updateAd(adDto);
        adService.deleteAd(adId);
        List<AdTypeDto> adTypeDtoList = adTypeService.getAllAdTypes();
        adTypeService.deleteAdType(adTypeDtoList.get(0).getId());
        credentialService.deleteCredential(credentialId);
        userService.deleteUser(userId);

    }

    @Test
    public void test7getAllAds() {
        List<AdDto> adDtoList = adService.getAllAds();
        assertTrue(adDtoList.size() > 0);
    }
}

