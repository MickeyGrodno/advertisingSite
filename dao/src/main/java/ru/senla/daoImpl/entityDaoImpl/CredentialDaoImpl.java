package ru.senla.daoImpl.entityDaoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.senla.dao.entityDao.CredentialDao;
import ru.senla.daoImpl.AbstractDaoImpl;
import ru.senla.entity.Credential;

@Repository
public class CredentialDaoImpl extends AbstractDaoImpl implements CredentialDao {

    public CredentialDaoImpl() {
        super(Credential.class);
    }

    @Autowired
    private SessionFactory factory;

    public Credential getCredentialByLogin(String login) {
        return (Credential) factory.getCurrentSession().createQuery("from Credential as c where c.login = :login")
                .setString("login", login).uniqueResult();
    }
}
