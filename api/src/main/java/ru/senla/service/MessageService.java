package ru.senla.service;
import ru.senla.dto.MessageDto;
import ru.senla.entity.Message;
import ru.senla.entity.User;

import java.util.List;

public interface MessageService {

    MessageDto getMessageById(Long id);

    Long saveMessage(MessageDto message);

    void updateMessage(MessageDto message);

    void deleteMessage(Long id);

    List<MessageDto> getAllMessages();

    void writeMessagesToCsvFromDb();

    void readMessagesFromCsvToDb();

    List<MessageDto> getAllMessagesByChatName(String chatName);
}
