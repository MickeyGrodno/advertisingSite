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
import ru.senla.entity.Ad;
import ru.senla.entity.AdType;
import ru.senla.entity.Credential;
import ru.senla.entity.User;
import ru.senla.service.AdService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdServiceTest {
    @Autowired
    private AdService adService;

    private static Ad ad;
    private static Long adId;
    private static AdType adType;
    private static Credential credential;
    private static Long userId;

    @BeforeClass
    public static void createTestData() {
        User user = new User("Sergei", true, new Date(), 1);
        adType = new AdType("Auto", "Mersedes", true);
        credential = new Credential();
        credential.setEmail("serg@mail.ru");
        credential.setLogin("MyLogin");
        credential.setPassword("mypassword");
        credential.setRole("admin");
        credential.setUser(user);
        user.setCredential(credential);
        ad = new Ad(user, adType, "Test message", new Date(), new Date());
        userId = ad.getUser().getId();
    }

    @Test
    public void aSave() {
        adId = adService.saveAd(ad);
    }

    @Test
    public void bGetByUserId(){
        List<Ad> ads = adService.getAdsByUserId((long) 3);
        assertNotNull(ads);
    }
    @Test
    public void searchByAdMessageText() {
        List<Ad> adList = adService.searchByAdMessageText("Test");
        assertTrue(adList.size() > 0);
    }

    @Test
    public void searchAdByAdType() {

        List<Ad> adList = adService.searchAdByAdType(adType);
        assertTrue(adList.size() > 0);
    }

    @Test
    public void searchAdByUserLogin() {
        List<Ad> adList = adService.searchAdByUserLogin("MyLogin");
        assertTrue(adList.size() > 0);
    }
    @Test
    public void getAdById() {
        Ad ad = adService.getAdById(adId);
        assertNotNull(ad);
    }

    @Test
    public void updateAndDeleteAd() {
        ad.setId(adId);
        ad.setAdMessage("new test Message");
        adService.updateAd(ad);
        adService.deleteAd(ad);
    }

    @Test
    public void getAllAds() {
        List<Ad> adList = adService.getAllAds();
        Ad ad = adList.stream().filter(x -> x.getAdMessage().equals("Test message")).findFirst().get();
        assertNotNull(ad);
    }


}
