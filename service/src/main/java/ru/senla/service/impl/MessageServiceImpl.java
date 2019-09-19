package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reflection.interfaces.CsvReader;
import reflection.interfaces.CsvWriter;
import ru.senla.dao.entityDao.MessageDao;
import ru.senla.entity.Message;
import ru.senla.service.MessageService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private static final Logger LOGGER = LogManager.getLogger(MessageServiceImpl.class.getName());
    private final MessageDao messageDao;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;

    @Autowired
    public MessageServiceImpl(MessageDao messageDao, CsvWriter csvWriter, CsvReader csvReader) {
        this.messageDao = messageDao;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
    }

    public Message getMessageById(Long id) {
        Message message = (Message) messageDao.read(id);
        LOGGER.info(() -> " Message with id: " + message.getId() + "has gotten from DB");
        return message;
    }

    public Long saveMessage(Message message) {
        Long id = (Long) messageDao.create(message);
        LOGGER.info(() -> "Message with id: " + id + "saved in DB");
        return id;
    }

    public void updateMessage(Message message) {
        messageDao.update(message);
        LOGGER.info(() -> " adType with id: " + message.getId() + " was updated");
    }

    public void deleteMessage(Message message) {
        messageDao.delete(message);
        LOGGER.info(() -> " adType with id: " + message.getId() + " was updated");
    }

    public List getAllMessages() {
        List messages = messageDao.findAll(Message.class);
        LOGGER.info(() -> "all messages have gotten from DB");
        return messages;
    }

    public void writeMessagesToCsvFromDb() {
        csvWriter.writeToCsvFile(getAllMessages());
        LOGGER.info(() -> "all messages saved to CSV");
    }

    public void readMessagesFromCsvToDb() {
        List<Message> messages = (List<Message>) csvReader.readerFromCsv(Message.class);
        for (Object message : messages) {
            messageDao.saveOrUpdate(message);
        }
        LOGGER.info(() -> "all messages saved to DB");
    }
}
