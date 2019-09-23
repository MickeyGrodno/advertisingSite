package ru.senla.dao.entityDao;

import ru.senla.dao.AbstractDao;
import ru.senla.entity.Credential;

public interface CredentialDao extends AbstractDao {

    Credential getCredentialByLogin(String login);

}
