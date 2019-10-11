package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.senla.dao.entityDao.ChatDao;
import ru.senla.dto.ChatDto;
import ru.senla.entity.AdType;
import ru.senla.entity.Chat;
import ru.senla.service.ChatService;
import ru.senla.service.EntityToDtoConverter;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

    private static final Logger LOGGER = LogManager.getLogger(ChatServiceImpl.class.getName());
    private final ChatDao chatDao;
    private final EntityToDtoConverter entityToDtoConverter;
    private final String chatId = "chat with id: ";

    @Autowired
    public ChatServiceImpl(ChatDao chatDao, EntityToDtoConverter entityToDtoConverter) {
        this.chatDao = chatDao;
        this.entityToDtoConverter = entityToDtoConverter;
    }

    public ChatDto getChatById(Long id) {
        Chat chat = (Chat) chatDao.read(id);
        ChatDto chatDto = entityToDtoConverter.chatToChatDto(chat);
        LOGGER.info(() -> chatId + chat.getId() + "has gotten from DB");
        return chatDto;
    }

    public Long saveChat(ChatDto chatDto) {
        Chat chat = entityToDtoConverter.chatDtoToChat(chatDto);
        Long id = (Long) chatDao.create(chat);
        LOGGER.info(() -> chatId + id + "saved in DB");
        return id;
    }

    public void updateChat(ChatDto chatDto) {
        Chat chat = entityToDtoConverter.chatDtoToChat(chatDto);
        chatDao.update(chat);
        LOGGER.info(() -> chatId + chat.getId() + " was updated");
    }

    public void deleteChat(Long id) {
        Chat chat = (Chat) chatDao.load(id);
        chatDao.delete(chat);
        LOGGER.info(() -> chatId + id + " was deleted");
    }

    public List<ChatDto> getAllChats() {
        List<Chat> chats = chatDao.findAll(Chat.class);
        List<ChatDto> chatDtoList = entityToDtoConverter.chatListToChatDtoList(chats);
        LOGGER.info(() -> "all chats have gotten from DB");
        return chatDtoList;
    }

    public List<String> getUserChatNames(Long userId) {
        List<String> chatNames = chatDao.getUserChatNames(userId);
        LOGGER.info(() -> "all user chats have gotten from DB");
        return chatNames;
    }
}
