package ru.senla.daoImpl.entityDaoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.senla.dao.entityDao.ChatDao;
import ru.senla.daoImpl.AbstractDaoImpl;
import ru.senla.entity.Chat;
import ru.senla.entity.User;
import java.util.List;

@Repository
public class ChatDaoImpl extends AbstractDaoImpl implements ChatDao {

    public ChatDaoImpl() {
        super(Chat.class);
    }

    @Autowired
    private SessionFactory factory;

    public List<String> getUserChatNames(User user) {
        return factory.getCurrentSession().createQuery("select c.chatName from Chat c " +
                "join c.userList u where u.id = :id")
                .setLong("id", user.getId())
                .list();
    }
}
