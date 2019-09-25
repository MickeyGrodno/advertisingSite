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
import ru.senla.entity.Chat;
import ru.senla.entity.Message;
import ru.senla.entity.User;
import ru.senla.service.ChatService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ChatServiceTest {

    private ChatService chatService;
    private static Chat chat;
    private static Long id;
    private static User user;

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    @BeforeClass
    public static void init() {
        List<User> userList = new ArrayList<>();
        List<Message> messageList = new ArrayList<>();
        Message message = new Message();
        user = new User("Sergei", true, new Date(), 1);
        chat = new Chat();
        userList.add(user);
        message.setChat(chat);
        message.setUser(user);
        message.setMessageDate(new Date());
        message.setText("Test text");
        chat.setChatName("Chat 1");
        chat.setUserList(userList);
        chat.setMessageList(messageList);
    }

    @Test
    public void aSave() {
        id = chatService.saveChat(chat);
    }

    @Test
    public void bGetCredentialById() {
        Chat chatFromDb = chatService.getChatById(id);
        assertEquals("Chat 1", chatFromDb.getChatName());
    }

    @Test
    public void getChatNames() {
        user.setId((long) 9);
        List<String> chatNames = chatService.getUserChatNames(user);
        assertTrue(chatNames.size() > 0);
    }

    @Test
    public void updateChat() {
        chat.setChatName("Chat 2");
        chatService.updateChat(chat);
    }

    @Test
    public void getAllChats() {
        List<Chat> chatList = chatService.getAllChats();
        assertNotNull(chatList);
    }

    @Test
    public void zDelete() {
        chatService.deleteChat(chat);
    }
}
