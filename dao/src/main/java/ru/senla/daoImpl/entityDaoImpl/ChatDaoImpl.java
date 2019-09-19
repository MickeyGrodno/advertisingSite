package ru.senla.daoImpl.entityDaoImpl;

import org.springframework.stereotype.Repository;
import ru.senla.dao.entityDao.ChatDao;
import ru.senla.daoImpl.AbstractDaoImpl;
import ru.senla.entity.Chat;

@Repository
public class ChatDaoImpl extends AbstractDaoImpl implements ChatDao {

    public ChatDaoImpl() {
        super(Chat.class);
    }

}
