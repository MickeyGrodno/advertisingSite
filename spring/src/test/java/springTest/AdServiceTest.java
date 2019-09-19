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
import ru.senla.entity.User;
import ru.senla.service.AdService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AdServiceTest {

    private AdService adService;

    @Autowired
    public void setAdService(AdService adService) {
        this.adService = adService;
    }

    private static Ad ad;
    private static Long adId;

    @BeforeClass
    public static void createTestData() {
        User user = new User("Sergei", true, new Date(), 1);
        AdType adType = new AdType("sdasd", "dsadsad", true);
        ad = new Ad(user, adType, "Test message", new Date(), new Date());
    }

    @Test
    public void aSave() {
        adId = adService.saveAd(ad);
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
