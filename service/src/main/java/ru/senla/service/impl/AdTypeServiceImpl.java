package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reflection.interfaces.CsvReader;
import reflection.interfaces.CsvWriter;
import ru.senla.dao.entityDao.AdTypeDao;
import ru.senla.entity.AdType;
import ru.senla.service.AdTypeService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AdTypeServiceImpl implements AdTypeService {

    private static final Logger LOGGER = LogManager.getLogger(AdTypeServiceImpl.class.getName());
    private final AdTypeDao adTypeDao;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;

    @Autowired
    public AdTypeServiceImpl(AdTypeDao adTypeDao, CsvWriter csvWriter, CsvReader csvReader) {
        this.adTypeDao = adTypeDao;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
    }

    public AdType getAdTypeById(Long id) {
        AdType adType = (AdType) adTypeDao.read(id);
        LOGGER.info(() -> " adType with id: " + adType.getId() + "has gotten from DB");
        return adType;
    }

    public Long saveAdType(AdType adType) {
        Long id = (Long) adTypeDao.create(adType);
        LOGGER.info(() -> " adType with id: " + id + "saved in DB");
        return id;
    }

    public void updateAdType(AdType adType) {
        adTypeDao.update(adType);
        LOGGER.info(() -> " adType with id: " + adType.getId() + " was updated");
    }

    public void deleteAdType(AdType adType) {
        adTypeDao.delete(adType);
        LOGGER.info(() -> " adType with id: " + adType.getId() + " was deleted");
    }

    public List getAllAdTypes() {
        List ads = adTypeDao.findAll(AdType.class);
        LOGGER.info(() -> "all adTypes have gotten from DB");
        return ads;
    }

    public void writeAdTypesToCsvFromDb() {
        csvWriter.writeToCsvFile(getAllAdTypes());
        LOGGER.info(() -> "all users saved to CSV");
    }

    public void readAdTypesFromCsvToDb() {
        List<AdType> AdTypes = (List<AdType>) csvReader.readerFromCsv(AdType.class);
        for (Object adType : AdTypes) {
            adTypeDao.saveOrUpdate(adType);
        }
        LOGGER.info(() -> "all users saved to DB");
    }
}
