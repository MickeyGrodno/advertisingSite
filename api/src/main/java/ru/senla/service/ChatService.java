package ru.senla.service;

import ru.senla.dto.ChatDto;
import ru.senla.entity.Chat;
import ru.senla.entity.User;

import java.util.List;

public interface ChatService {

    ChatDto getChatById(Long id);

    Long saveChat(ChatDto chatDto);

    void updateChat(ChatDto chatDto);

    void deleteChat(Long id);

    List getAllChats();

    void writeChatsToCsvFromDb();

    void readChatsFromCsvToDb();

    List<String> getUserChatNames(Long userId);
}
