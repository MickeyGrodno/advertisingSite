package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reflection.interfaces.CsvReader;
import reflection.interfaces.CsvWriter;
import ru.senla.dao.entityDao.CredentialDao;
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
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;
    private final EntityToDtoConverter entityToDtoConverter;

    @Autowired
    public CredentialServiceImpl(CredentialDao credentialDao, CsvWriter csvWriter, CsvReader csvReader,
                                 EntityToDtoConverter entityToDtoConverter) {
        this.credentialDao = credentialDao;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
        this.entityToDtoConverter = entityToDtoConverter;
    }

    public CredentialDto getCredentialById(Long id) {
        Credential credential = (Credential) credentialDao.read(id);
        CredentialDto credentialDto = entityToDtoConverter.credentialToCredentialDto(credential);
        LOGGER.info(() -> " Credential with id: " + credential.getCredentialId() + "has gotten from DB");
        return credentialDto;
    }

    public Long saveCredential(CredentialDto credentialDto) {
        Credential credential = entityToDtoConverter.credentialDtoToCredential(credentialDto);
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
        Credential credential = (Credential) credentialDao.read(id);
        credentialDao.delete(credential);
        LOGGER.info(() -> " Credential with UserId: " + credential.getCredentialId() + " was deleted");
    }

    public List getAllCredentials() {
        List<Credential> ads = credentialDao.findAll(Credential.class);
        List<CredentialDto> adDtoList = entityToDtoConverter.credentialListToCredentialDtoList(ads);
        LOGGER.info(() -> "all credentials have gotten from DB");
        return adDtoList;
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
