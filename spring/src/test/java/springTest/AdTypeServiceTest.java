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
import ru.senla.dto.AdTypeDto;
import ru.senla.entity.AdType;
import ru.senla.service.AdTypeService;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class AdTypeServiceTest {
    private AdTypeService adTypeService;
    private static AdTypeDto adTypeDto;
    private static Long id;

    @Autowired
    public void setAdTypeService(AdTypeService adTypeService) {
        this.adTypeService = adTypeService;
    }

    @BeforeClass
    public static void init() {
        adTypeDto = new AdTypeDto();
        adTypeDto.setCategory("Мебель");
        adTypeDto.setClassification("стулья");
        adTypeDto.setBuyOrSale(true);
    }

    @Test
    public void aSave() {
        id = adTypeService.saveAdType(adTypeDto);
    }

    @Test
    public void bgetAdTypeById() {
        AdTypeDto adTypeDtoFromDb = adTypeService.getAdTypeById(id);
        assertEquals("стулья", adTypeDtoFromDb.getClassification());
    }

    @Test
    public void updateAndDeleteAdType() {
        adTypeDto.setId(id);
        adTypeDto.setCategory("Авто");
        adTypeService.updateAdType(adTypeDto);
        adTypeService.deleteAdType(id);
    }

    @Test
    public void cGetAllAdTypes() {
        List<AdTypeDto> adTypeDtoList = adTypeService.getAllAdTypes();
        assertNotNull(adTypeDtoList);
    }
}
