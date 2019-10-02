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
import ru.senla.entity.Chat;
import ru.senla.entity.Message;
import ru.senla.entity.User;
import ru.senla.service.MessageService;

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

public class MessageServiceTest {
    private static MessageService messageService;
    private static Message message;
    private static Long id;
    private static User user;
    private static Chat chat;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @BeforeClass
    public static void init() {
        message = new Message();
        chat = new Chat();
        user = new User("Sergei", true, new Date(), 1);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        chat.setChatName("First chat");
        chat.setUserList(userList);
        message.setChat(chat);
        message.setUser(user);
        message.setMessageDate(new Date());
        message.setText("Test text");
    }

//    @Test
//    public void aSave() {
//        id = messageService.saveMessage(message);
//    }
//
//    @Test
//    public void getMessageById() {
//        Message messageFromDb = messageService.getMessageById(id);
//        assertEquals("Test text", messageFromDb.getText());
//    }
//
//    @Test
//    public void getMessagesByChatName() {
//        List<Message> messages = messageService.getAllMessagesByChatName(chat.getChatName());
//        assertTrue(messages.size() > 0);
//    }
//
//    @Test
//    public void updateAndDeleteMessage() {
//        message.setId(id);
//        message.setText("New Text");
//        messageService.updateMessage(message);
//        messageService.deleteMessage(message);
//    }
//
//    @Test
//    public void getAllMessages() {
//        List<Message> messageList = messageService.getAllMessages();
//        assertNotNull(messageList);
//    }
}
