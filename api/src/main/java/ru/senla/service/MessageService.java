package ru.senla.service;
import ru.senla.entity.Message;
import ru.senla.entity.User;

import java.util.List;

public interface MessageService {

    Message getMessageById(Long id);

    Long saveMessage(Message message);

    void updateMessage(Message message);

    void deleteMessage(Message message);

    List getAllMessages();

    void writeMessagesToCsvFromDb();

    void readMessagesFromCsvToDb();

    List<Message> getAllMessagesByChatName(String chatName);
}
