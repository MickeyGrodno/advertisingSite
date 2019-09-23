package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reflection.interfaces.CsvReader;
import reflection.interfaces.CsvWriter;
import ru.senla.dao.entityDao.CredentialDao;
import ru.senla.entity.Credential;
import ru.senla.service.CredentialService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CredentialServiceImpl implements CredentialService {

    private static final Logger LOGGER = LogManager.getLogger(CredentialServiceImpl.class.getName());
    private final CredentialDao credentialDao;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;

    @Autowired
    public CredentialServiceImpl(CredentialDao credentialDao, CsvWriter csvWriter, CsvReader csvReader) {
        this.credentialDao = credentialDao;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
    }

    public Credential getCredentialById(Long id) {
        Credential credential = (Credential) credentialDao.read(id);
        LOGGER.info(() -> " Credential with id: " + credential.getCredentialId() + "has gotten from DB");
        return credential;
    }

    public Long saveCredential(Credential credential) {
        Long userId = (Long) credentialDao.create(credential);
        LOGGER.info(() -> " Credential with UserId: " + userId + "saved in DB");
        return userId;
    }

    public void updateCredential(Credential credential) {
        credentialDao.update(credential);
        LOGGER.info(() -> " Credential with UserId: " + credential.getCredentialId() + " was updated");
    }

    public void deleteCredential(Credential Credential) {
        credentialDao.delete(Credential);
        LOGGER.info(() -> " Credential with UserId: " + Credential.getCredentialId() + " was deleted");
    }

    public List getAllCredentials() {
        List ads = credentialDao.findAll(Credential.class);
        LOGGER.info(() -> "all Credentials have gotten from DB");
        return ads;
    }

    public void writeCredentialsToCsvFromDb() {
        csvWriter.writeToCsvFile(getAllCredentials());
        LOGGER.info(() -> "all credentials saved to CSV");
    }

    public void readCredentialsFromCsvToDb() {
        List<Credential> credentials = (List<Credential>) csvReader.readerFromCsv(Credential.class);
        for (Credential credential : credentials) {
            credentialDao.saveOrUpdate(credential);
        }
        LOGGER.info(() -> "all credentials saved to DB");
    }
}
