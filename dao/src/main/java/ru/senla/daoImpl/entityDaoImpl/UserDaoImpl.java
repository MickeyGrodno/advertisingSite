package ru.senla.daoImpl.entityDaoImpl;

import org.springframework.stereotype.Repository;
import ru.senla.dao.entityDao.UserDao;
import ru.senla.daoImpl.AbstractDaoImpl;
import ru.senla.entity.User;

@Repository
public class UserDaoImpl extends AbstractDaoImpl implements UserDao {

    public UserDaoImpl() {
        super(User.class);
    }

}
