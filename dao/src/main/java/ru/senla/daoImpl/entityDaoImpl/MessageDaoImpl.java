package ru.senla.daoImpl.entityDaoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.senla.dao.entityDao.MessageDao;
import ru.senla.daoImpl.AbstractDaoImpl;
import ru.senla.entity.Message;

import java.util.List;

@Repository
public class MessageDaoImpl extends AbstractDaoImpl implements MessageDao {

    public MessageDaoImpl() {
        super(Message.class);
    }

    @Autowired
    SessionFactory factory;

    public List<Message> getMessagesByChatName(String chatName) {
        return factory.getCurrentSession().createQuery("select c.messageList from Chat c " +
                "where c.chatName = :chatName")
                .setString("chatName", chatName)
                .list();
    }
}
