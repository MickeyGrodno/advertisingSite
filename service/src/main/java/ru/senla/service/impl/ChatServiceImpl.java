package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reflection.interfaces.CsvReader;
import reflection.interfaces.CsvWriter;
import ru.senla.dao.entityDao.ChatDao;
import ru.senla.dto.ChatDto;
import ru.senla.entity.AdType;
import ru.senla.entity.Chat;
import ru.senla.entity.User;
import ru.senla.service.ChatService;
import ru.senla.service.EntityToDtoConverter;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

    private static final Logger LOGGER = LogManager.getLogger(ChatServiceImpl.class.getName());
    private final ChatDao chatDao;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;
    private final EntityToDtoConverter entityToDtoConverter;

    @Autowired
    public ChatServiceImpl(ChatDao chatDao, CsvWriter csvWriter, CsvReader csvReader,
                           EntityToDtoConverter entityToDtoConverter) {
        this.chatDao = chatDao;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
        this.entityToDtoConverter = entityToDtoConverter;
    }

    public ChatDto getChatById(Long id) {
        Chat chat = (Chat) chatDao.read(id);
        ChatDto chatDto = entityToDtoConverter.chatToChatDto(chat);
        LOGGER.info(() -> " chat with id: " + chat.getId() + "has gotten from DB");
        return chatDto;
    }

    public Long saveChat(ChatDto chatDto) {
        Chat chat = entityToDtoConverter.chatDtoToChat(chatDto);
        Long id = (Long) chatDao.create(chat);
        LOGGER.info(() -> " chat with id: " + id + "saved in DB");
        return id;
    }

    public void updateChat(ChatDto chatDto) {
        Chat chat = entityToDtoConverter.chatDtoToChat(chatDto);
        chatDao.update(chat);
        LOGGER.info(() -> " chat with id: " + chat.getId() + " was updated");
    }

    public void deleteChat(Long id) {
        Chat chat = (Chat) chatDao.read(id);
        chatDao.delete(chat);
        LOGGER.info(() -> " chat with id: " + id + " was deleted");
    }

    public List<ChatDto> getAllChats() {
        List<Chat> chats = chatDao.findAll(Chat.class);
        List<ChatDto> chatDtoList = entityToDtoConverter.chatListToChatDtoList(chats);
        LOGGER.info(() -> "all chats have gotten from DB");
        return chatDtoList;
    }

    public void writeChatsToCsvFromDb() {
        csvWriter.writeToCsvFile(getAllChats());
        LOGGER.info(() -> "all chats saved to CSV");
    }

    public void readChatsFromCsvToDb() {
        List<Chat> chats = (List<Chat>) csvReader.readerFromCsv(AdType.class);
        for (Object chat : chats) {
            chatDao.saveOrUpdate(chat);
        }
        LOGGER.info(() -> "all chats saved to DB");
    }

    public List<String> getUserChatNames(Long userId) {
        List<String> chatNames = chatDao.getUserChatNames(userId);
        LOGGER.info(() -> "all user chats have gotten from DB");
        return chatNames;
    }
}
