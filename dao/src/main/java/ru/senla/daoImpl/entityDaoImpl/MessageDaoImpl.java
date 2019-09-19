package ru.senla.daoImpl.entityDaoImpl;

import org.springframework.stereotype.Repository;
import ru.senla.dao.entityDao.MessageDao;
import ru.senla.daoImpl.AbstractDaoImpl;
import ru.senla.entity.Message;

@Repository
public class MessageDaoImpl extends AbstractDaoImpl implements MessageDao {

    public MessageDaoImpl() {
        super(Message.class);
    }

}
