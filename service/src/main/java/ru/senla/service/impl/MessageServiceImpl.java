package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.senla.dao.entityDao.ChatDao;
import ru.senla.dao.entityDao.MessageDao;
import ru.senla.dto.MessageDto;
import ru.senla.entity.Chat;
import ru.senla.entity.Message;
import ru.senla.service.EntityToDtoConverter;
import ru.senla.service.MessageService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {

    private static final Logger LOGGER = LogManager.getLogger(MessageServiceImpl.class.getName());
    private final MessageDao messageDao;
    private final EntityToDtoConverter entityToDtoConverter;
    private final ChatDao chatDao;

    @Autowired
    public MessageServiceImpl(MessageDao messageDao,
                              EntityToDtoConverter entityToDtoConverter, ChatDao chatDao) {
        this.messageDao = messageDao;
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
        if (messageDto.getChatId() != null) {
            Chat chat = (Chat) chatDao.load(messageDto.getChatId());
            chat.getMessageList().add(message);
            message.setChat(chat);
        }

        Long id = (Long) messageDao.create(message);
        LOGGER.info(() -> "Message with id: " + id + "saved in DB");
        return id;
    }

    public void updateMessage(MessageDto messageDto) {
        Message message = entityToDtoConverter.messageDtoToMessage(messageDto);
        messageDao.update(message);
        LOGGER.info(() -> " adType with id: " + message.getId() + " was updated");
    }

    public void deleteMessage(Long id) {
        Message message = (Message) messageDao.load(id);
        messageDao.delete(message);
        LOGGER.info(() -> " adType with id: " + message.getId() + " was updated");
    }

    public List<MessageDto> getAllMessages() {
        List<Message> messages = messageDao.findAll(Message.class);
        List<MessageDto> messageDtoList = entityToDtoConverter.messageListToMessageDtoList(messages);
        LOGGER.info(() -> "all messages have gotten from DB");
        return messageDtoList;
    }

    public List<MessageDto> getAllMessagesByChatName(String chatName) {
        List<Message> messages = messageDao.getMessagesByChatName(chatName);
        List<MessageDto> messageDtoList = entityToDtoConverter.messageListToMessageDtoList(messages);
        LOGGER.info(() -> "all messages by chat name " + chatName + " gotten from DB");
        return messageDtoList;
    }
}
