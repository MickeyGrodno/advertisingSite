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
import ru.senla.entity.Comment;
import ru.senla.entity.User;
import ru.senla.service.CommentService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CommentServiceTest {
    private static CommentService commentService;
    private static Comment comment;
    private static Long id;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @BeforeClass
    public static void init() {
        User user = new User("Sergei", true, new Date(), 1);
        AdType adType = new AdType("sdasd", "dsadsad", true);
        Ad ad = new Ad(user, adType, "Test message", new Date(), new Date());
        comment = new Comment();
        comment.setAd(ad);
        comment.setCommentDate(new Date());
        comment.setCommentMessage("Comment 1");
        comment.setUser(new User("Sergei", true, new Date(), 1));
    }

    @Test
    public void aSave() {
        id = commentService.saveComment(comment);
    }

    @Test
    public void getCommentById() {
        Comment commentFromDb = commentService.getCommentById(id);
        assertEquals("Comment 1", commentFromDb.getCommentMessage());
    }

    @Test
    public void getAllComments() {
        List<Comment> commentList = commentService.getAllComments();
        assertNotNull(commentList);
    }

    @Test
    public void updateAndDeleteComment() {
        comment.setId(id);
        comment.setCommentMessage("Comment 2");
        commentService.updateComment(comment);
        commentService.deleteComment(comment);
    }
}
