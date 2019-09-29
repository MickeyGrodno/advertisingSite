package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reflection.interfaces.CsvReader;
import reflection.interfaces.CsvWriter;
import ru.senla.dao.entityDao.ChatDao;
import ru.senla.dao.entityDao.MessageDao;
import ru.senla.dto.MessageDto;
import ru.senla.entity.Chat;
import ru.senla.entity.Message;
import ru.senla.entity.User;
import ru.senla.service.EntityToDtoConverter;
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
    private final EntityToDtoConverter entityToDtoConverter;
    private final ChatDao chatDao;

    @Autowired
    public MessageServiceImpl(MessageDao messageDao, CsvWriter csvWriter, CsvReader csvReader,
                              EntityToDtoConverter entityToDtoConverter, ChatDao chatDao) {
        this.messageDao = messageDao;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
        this.entityToDtoConverter = entityToDtoConverter;
        this.chatDao = chatDao;
    }

    public MessageDto getMessageById(Long id) {
        Message message = (Message) messageDao.read(id);
        MessageDto messageDto = entityToDtoConverter.messageToMessageDto(message);
        LOGGER.info(() -> " Message with id: " + message.getId() + "has gotten from DB");
        return messageDto;
    }

    public Long saveMessage(MessageDto messageDto) {
        Message message = entityToDtoConverter.messageDtoToMessage(messageDto);
        Chat chat = (Chat) chatDao.read(messageDto.getChatId());
        message.setChat(chat);
        Long id = (Long) messageDao.create(message);
        LOGGER.info(() -> "Message with id: " + id + "saved in DB");
        return id;
    }

    public void updateMessage(MessageDto messageDto) {
        Message message = entityToDtoConverter.messageDtoToMessage(messageDto);
        Chat chat = (Chat) chatDao.read(messageDto.getChatId());
        message.setChat(chat);
        messageDao.update(message);
        LOGGER.info(() -> " adType with id: " + message.getId() + " was updated");
    }

    public void deleteMessage(Long id) {
        Message message = (Message) messageDao.read(id);
        messageDao.delete(message);
        LOGGER.info(() -> " adType with id: " + message.getId() + " was updated");
    }

    public List<MessageDto> getAllMessages() {
        List<Message> messages = messageDao.findAll(Message.class);
        List<MessageDto> messageDtoList = entityToDtoConverter.messageListToMessageDtoList(messages);
        LOGGER.info(() -> "all messages have gotten from DB");
        return messageDtoList;
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

    public List<MessageDto> getAllMessagesByChatName(String chatName) {
        List<Message> messages = messageDao.getMessagesByChatName(chatName);
        List<MessageDto> messageDtoList = entityToDtoConverter.messageListToMessageDtoList(messages);
        LOGGER.info(() -> "all messages by chat name "+chatName+" gotten from DB");
        return messageDtoList;
    }
}
