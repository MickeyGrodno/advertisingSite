package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reflection.AnnotationCSVReader;
import reflection.AnnotationCSVWriter;
import reflection.interfaces.CsvReader;
import reflection.interfaces.CsvWriter;
import ru.senla.dao.entityDao.ChatDao;
import ru.senla.entity.AdType;
import ru.senla.entity.Chat;
import ru.senla.entity.User;
import ru.senla.service.ChatService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

    private static final Logger LOGGER = LogManager.getLogger(ChatServiceImpl.class.getName());
    private final ChatDao chatDao;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;

    @Autowired
    public ChatServiceImpl(ChatDao chatDao, CsvWriter csvWriter, CsvReader csvReader) {
        this.chatDao = chatDao;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
    }

    public Chat getChatById(Long id) {
        Chat chat = (Chat) chatDao.read(id);
        LOGGER.info(() -> " chat with id: " + chat.getId() + "has gotten from DB");
        return chat;
    }

    public Long saveChat(Chat chat) {

        Long id = (Long) chatDao.create(chat);
        LOGGER.info(() -> " adType with id: " + id + "saved in DB");
        return id;
    }

    public void updateChat(Chat chat) {

        chatDao.update(chat);
        LOGGER.info(() -> " adType with id: " + chat.getId() + " was updated");
    }

    public void deleteChat(Chat chat) {
            chatDao.delete(chat);
            LOGGER.info(() -> " adType with id: " + chat.getId() + " was deleted");
    }

    public List getAllChats() {
        List ads = chatDao.findAll(AdType.class);
        LOGGER.info(() -> "all adTypes have gotten from DB");
        return ads;
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
}
