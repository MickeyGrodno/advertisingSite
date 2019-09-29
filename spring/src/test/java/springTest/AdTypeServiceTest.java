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
import ru.senla.entity.AdType;
import ru.senla.service.AdTypeService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AdTypeServiceTest {
    private AdTypeService adTypeService;
    private static AdType adType;
    private static Long id;

    @Autowired
    public void setAdTypeService(AdTypeService adTypeService) {
        this.adTypeService = adTypeService;
    }

    @BeforeClass
    public static void init() {
        adType = new AdType("Мебель", "для кухни", true);

    }
//
//    @Test
//    public void aSave() {
//        id = adTypeService.saveAdType(adType);
//    }
//
//    @Test
//    public void bgetAdTypeById() {
//        AdType adTypeFromDb = adTypeService.getAdTypeById(id);
//        assertEquals("для кухни", adTypeFromDb.getClassification());
//    }
//
//    @Test
//    public void updateAndDeleteAdType() {
//        adType.setId(id);
//        adType.setCategory("Авто");
//        adTypeService.updateAdType(adType);
//        adTypeService.deleteAdType(adType);
//    }
//
//    @Test
//    public void cGetAllAdTypes() {
//        List<AdType> adTypeList = adTypeService.getAllAdTypes();
//        assertNotNull(adTypeList);
//    }
}
