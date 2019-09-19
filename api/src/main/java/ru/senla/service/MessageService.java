package ru.senla.service;
import ru.senla.entity.Message;
import java.util.List;

public interface MessageService {

    Message getMessageById(Long id);

    Long saveMessage(Message message);

    void updateMessage(Message message);

    void deleteMessage(Message message);

    List getAllMessages();

    void writeMessagesToCsvFromDb();

    void readMessagesFromCsvToDb();
}
