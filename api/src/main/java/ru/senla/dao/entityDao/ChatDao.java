package ru.senla.dao.entityDao;

import ru.senla.dao.AbstractDao;
import ru.senla.entity.User;
import java.util.List;

public interface ChatDao extends AbstractDao {
    List<String> getUserChatNames(User user);
}
