package ru.senla.daoImpl.entityDaoImpl;

import org.springframework.stereotype.Repository;
import ru.senla.dao.entityDao.CredentialDao;
import ru.senla.daoImpl.AbstractDaoImpl;
import ru.senla.entity.Credential;

@Repository
public class CredentialDaoImpl extends AbstractDaoImpl implements CredentialDao {

    public CredentialDaoImpl() {
        super(Credential.class);
    }

}
