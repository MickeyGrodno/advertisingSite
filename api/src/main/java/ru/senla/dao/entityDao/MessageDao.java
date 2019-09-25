package ru.senla.dao.entityDao;

import ru.senla.dao.AbstractDao;
import ru.senla.entity.Message;

import java.util.List;

public interface MessageDao extends AbstractDao {
    List<Message> getMessagesByChatName(String chatName);
}
