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
import ru.senla.dto.ChatDto;
import ru.senla.dto.CredentialDto;
import ru.senla.dto.MessageDto;
import ru.senla.dto.UserDto;
import ru.senla.entity.Chat;
import ru.senla.entity.Message;
import ru.senla.entity.User;
import ru.senla.service.ChatService;
import ru.senla.service.CredentialService;
import ru.senla.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class ChatServiceTest {

    private ChatService chatService;
    private CredentialService credentialService;
    private UserService userService;
    private static ChatDto chatDto;
    private static Long chatId;
    private static UserDto userDto;
    private static CredentialDto credentialDto;
    private static MessageDto messageDto;
    private static Long credentialId;
    private static Long userId;

    @Autowired
    public void setCredentialService(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @Autowired
    public void setChatService(ChatService chatService) {
        this.chatService = chatService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @BeforeClass
    public static void init() {
        userDto = new UserDto();
        userDto.setFirstName("Dmitry");
        userDto.setGender(true);
        userDto.setBirthDate(new Date());
        userDto.setUserRating(15);

        credentialDto = new CredentialDto();
        credentialDto.setEmail("saaaa@mail.ru");
        credentialDto.setLogin("Login7");
        credentialDto.setPassword("mypassword");
        credentialDto.setRole("admin");
        messageDto = new MessageDto();
        chatDto = new ChatDto();

    }

    @Test
    public void test1Save() {
        List<UserDto> userDtoList = new ArrayList<>();
        List<MessageDto> messageDtoList = new ArrayList<>();

        credentialId = credentialService.saveCredential(credentialDto);
        credentialDto.setCredentialId(credentialId);
        userDto.setCredentialId(credentialId);
        userId = userService.saveUser(userDto);
        credentialDto.setUserId(userId);
        userDto.setId(userId);
        userDtoList.add(userDto);
        messageDto.setUserDto(userDto);
        messageDto.setMessageDate(new Date());
        messageDto.setText("Test text");
        messageDtoList.add(messageDto);
        chatDto.setChatName("Chat 1");
        chatDto.setUserDtoList(userDtoList);
        chatDto.setMessageDtoList(messageDtoList);
        chatId = chatService.saveChat(chatDto);
    }

    @Test
    public void test2GetChatById() {
        ChatDto chatFromDb = chatService.getChatById(chatId);
        assertEquals("Chat 1", chatFromDb.getChatName());
    }

    @Test
    public void test3getChatNames() {
        userDto.setId(userId);
        List<String> chatNames = chatService.getUserChatNames(userId);
        assertEquals("Chat 1", chatNames.get(0));
    }

    @Test
    public void test4updateChat() {
        chatDto.setId(chatId);
        chatDto.setChatName("Chat 2");
        chatService.updateChat(chatDto);
    }

    @Test
    public void test5getAllChats() {
        List<Chat> chatList = chatService.getAllChats();
        assertNotNull(chatList);
    }

    @Test
    public void test6Delete() {
        chatService.deleteChat(chatId);
        userService.deleteUser(userId);
        credentialService.deleteCredential(credentialId);
    }
}
