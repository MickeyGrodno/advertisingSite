package ru.senla.service;

import ru.senla.entity.Chat;
import ru.senla.entity.User;

import java.util.List;

public interface ChatService {

    Chat getChatById(Long id);

    Long saveChat(Chat adType);

    void updateChat(Chat adType);

    void deleteChat(Chat adType);

    List getAllChats();

    void writeChatsToCsvFromDb();

    void readChatsFromCsvToDb();

    List<String> getUserChatNames(User user);
}
