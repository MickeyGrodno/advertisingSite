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
import ru.senla.dto.CommentDto;
import ru.senla.dto.CredentialDto;
import ru.senla.dto.UserDto;
import ru.senla.entity.Ad;
import ru.senla.entity.AdType;
import ru.senla.entity.Comment;
import ru.senla.entity.User;
import ru.senla.service.AdService;
import ru.senla.service.AdTypeService;
import ru.senla.service.CommentService;
import ru.senla.service.CredentialService;
import ru.senla.service.UserService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CommentServiceTest {
    private AdService adService;
    private UserService userService;
    private AdTypeService adTypeService;
    private CommentService commentService;
    private CredentialService credentialService;
    private static CommentDto commentDto;
    private static Long commentId;
    private static AdTypeDto adTypeDto;
    private static UserDto userDto;
    private static AdDto adDto;
    private static CredentialDto credentialDto;
    private static Long adId;
    private static Long adTypeId;
    private static Long userId;
    private static Long credentialId;

    @Autowired
    public void setCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @Autowired
    public void setAdService(AdService adService) {
        this.adService = adService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setAdTypeService(AdTypeService adTypeService) {
        this.adTypeService = adTypeService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @BeforeClass
    public static void init() {

        credentialDto = new CredentialDto();
        credentialDto.setEmail("s@mail.ru");
        credentialDto.setLogin("Log");
        credentialDto.setPassword("mypassword");
        credentialDto.setRole("admin");

        userDto = new UserDto();
        userDto.setFirstName("Dmitry");
        userDto.setGender(true);
        userDto.setBirthDate(new Date());
        userDto.setUserRating(15);

        adTypeDto = new AdTypeDto();
        adTypeDto.setCategory("auto");
        adTypeDto.setClassification("Bmw");
        adTypeDto.setBuyOrSale(true);

        adDto = new AdDto();
        adDto.setUserDto(userDto);
        adDto.setAdTypeDto(adTypeDto);
        adDto.setAdMessage("Test adDto");
        adDto.setAdDate(new Date());
        adDto.setAdTopDate(new Date());

        commentDto = new CommentDto();
        commentDto.setCommentDate(new Date());
        commentDto.setCommentMessage("Comment 1");
    }

    @Test
    public void test1Save() {

        adTypeId = adTypeService.saveAdType(adTypeDto);
        credentialId = credentialService.saveCredential(credentialDto);
        userDto.setCredentialId(credentialId);
        userId = userService.saveUser(userDto);
        userDto.setId(userId);
        adTypeDto.setId(adTypeId);
        adDto.setUserDto(userDto);
        adDto.setAdTypeDto(adTypeDto);
        adId = adService.saveAd(adDto);
        commentDto.setAdId(adId);
        commentDto.setUserId(userId);
        commentId = commentService.saveComment(commentDto);
    }

    @Test
    public void test2getCommentById() {
        CommentDto commentFromDb = commentService.getCommentById(commentId);
        assertEquals("Comment 1", commentFromDb.getCommentMessage());
    }

    @Test
    public void test3getAllComments() {
        List<CommentDto> commentDtoList = commentService.getAllComments();
        assertTrue(commentDtoList.size() > 0);
    }

    @Test
    public void test4updateAndDeleteComment() {
        commentDto.setId(commentId);
        commentDto.setCommentMessage("Comment 2");
        commentService.updateComment(commentDto);
        commentService.deleteComment(commentId);
        adService.deleteAd(adId);
        adTypeService.deleteAdType(adTypeId);
        userService.deleteUser(userId);
    }
}
