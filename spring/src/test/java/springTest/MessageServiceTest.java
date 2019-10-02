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
import ru.senla.dto.MessageDto;
import ru.senla.dto.UserDto;
import ru.senla.service.ChatService;
import ru.senla.service.MessageService;
import ru.senla.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class MessageServiceTest {
    private ChatService chatService;
    private UserService userService;
    private MessageService messageService;
    private static MessageDto messageDto;
    private static Long messageId;
    private static Long userId;
    private static Long chatId;
    private static UserDto userDto;
    private static ChatDto chatDto;


    @Autowired
    public void setMessageService(MessageService messageService, ChatService chatService, UserService userService) {
        this.messageService = messageService;
        this.chatService = chatService;
        this.userService = userService;
    }

    @BeforeClass
    public static void init() {
        messageDto = new MessageDto();
        userDto = new UserDto();
        chatDto = new ChatDto();
        userDto.setFirstName("Sergei");
        userDto.setGender(true);
        userDto.setBirthDate(new Date());
        userDto.setUserRating(5);
        List<UserDto> userDtoList = new ArrayList<>();
        chatDto.setChatName("First chat");
        chatDto.setUserDtoList(userDtoList);
        messageDto.setChatId(1L);
        messageDto.setUserDto(userDto);
        messageDto.setMessageDate(new Date());
        messageDto.setText("Test text");
    }

    @Test
    public void test1Save() {
        userId = userService.saveUser(userDto);
        userDto.setId(userId);

        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList.add(userDto);
        List<MessageDto> messageDtoList = new ArrayList<>();

        chatDto.setMessageDtoList(messageDtoList);
        chatDto.setUserDtoList(userDtoList);
        chatDto.setMessageDtoList(messageDtoList);

        chatId = chatService.saveChat(chatDto);
        messageDto.setChatId(chatId);
        messageDto.setUserDto(userDto);
        messageId = messageService.saveMessage(messageDto);
    }

    @Test
    public void test2getMessageById() {
        MessageDto messageDtoFromDb = messageService.getMessageById(messageId);
        assertEquals("Test text", messageDtoFromDb.getText());
    }

    @Test
    public void test3getMessagesByChatName() {
        List<MessageDto> messageDtoList = messageService.getAllMessagesByChatName(chatDto.getChatName());
        assertEquals("Test text", messageDtoList.get(0).getText());
    }

    @Test
    public void test5updateAndDeleteMessage() {
        messageDto.setId(messageId);
        messageDto.setText("New Text");
        messageService.updateMessage(messageDto);
        messageService.deleteMessage(messageId);
        chatService.deleteChat(chatId);
        userService.deleteUser(userId);
    }

    @Test
    public void test4getAllMessages() {
        List<MessageDto> messageDtoList = messageService.getAllMessages();
        String messageText = messageDtoList.get(0).getText();
        assertEquals("Test text", messageText);
    }
}
