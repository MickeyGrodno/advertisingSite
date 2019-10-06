package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.senla.dao.entityDao.CredentialDao;
import ru.senla.dao.entityDao.UserDao;
import ru.senla.dto.CredentialDto;
import ru.senla.entity.Credential;
import ru.senla.service.CredentialService;
import ru.senla.service.EntityToDtoConverter;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CredentialServiceImpl implements CredentialService {

    private static final Logger LOGGER = LogManager.getLogger(CredentialServiceImpl.class.getName());
    private final CredentialDao credentialDao;
    private final EntityToDtoConverter entityToDtoConverter;
    private final UserDao userDao;

    @Autowired
    public CredentialServiceImpl(CredentialDao credentialDao,
                                 EntityToDtoConverter entityToDtoConverter, UserDao userDao) {
        this.credentialDao = credentialDao;
        this.entityToDtoConverter = entityToDtoConverter;
        this.userDao = userDao;

    }

    public CredentialDto getCredentialById(Long id) {
        Credential credential = (Credential) credentialDao.read(id);
        CredentialDto credentialDto = entityToDtoConverter.credentialToCredentialDto(credential);
        LOGGER.info(() -> " Credential with id: " + credential.getCredentialId() + "has gotten from DB");
        return credentialDto;
    }

    public Credential getCredentialByLogin(String login){
        return credentialDao.getCredentialByLogin(login);
    }

    public Long saveCredential(CredentialDto credentialDto) {
        Credential credential = entityToDtoConverter.credentialDtoToCredential(credentialDto);
//        if (credentialDto.getUserId() != null) {
//            User user = (User) userDao.load(credentialDto.getUserId());
//            user.setCredential(credential);
//            credential.setUser(user);
//        }
        Long id = (Long) credentialDao.create(credential);
        LOGGER.info(() -> " Credential with UserId: " + id + "saved in DB");
        return id;
    }

    public void updateCredential(CredentialDto credentialDto) {
        Credential credential = entityToDtoConverter.credentialDtoToCredential(credentialDto);
        credentialDao.update(credential);
        LOGGER.info(() -> " Credential with UserId: " + credential.getCredentialId() + " was updated");
    }

    public void deleteCredential(Long id) {
        Credential credential = (Credential) credentialDao.load(id);
        credentialDao.delete(credential);
        LOGGER.info(() -> " Credential with UserId: " + credential.getCredentialId() + " was deleted");
    }

    public List getAllCredentials() {
        List<Credential> ads = credentialDao.findAll(Credential.class);
        List<CredentialDto> adDtoList = entityToDtoConverter.credentialListToCredentialDtoList(ads);
        LOGGER.info(() -> "all credentials have gotten from DB");
        return adDtoList;
    }
}
