package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reflection.interfaces.CsvReader;
import reflection.interfaces.CsvWriter;
import ru.senla.dao.entityDao.AdDao;
import ru.senla.entity.Ad;
import ru.senla.service.AdService;
import java.util.List;

@Service
@Transactional
public class AdServiceImpl implements AdService {

    private static final Logger LOGGER = LogManager.getLogger(AdServiceImpl.class.getName());
    private final AdDao adDao;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;

    @Autowired
    public AdServiceImpl(AdDao adDao, CsvWriter csvWriter, CsvReader csvReader) {
        this.adDao = adDao;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
    }

    public Ad getAdById(Long id) {
        Ad ad = (Ad) adDao.read(id);
        LOGGER.info(() -> " ad with id: " + ad.getId() + "has gotten from DB");
        return ad;
    }

    public Long saveAd(Ad ad) {
        Long id = (Long) adDao.create(ad);
        LOGGER.info(() -> " ad with id: " + id + "saved in DB");
        return id;
    }

    public void updateAd(Ad ad) {
        adDao.update(ad);
        LOGGER.info(() -> " ad with id: " + ad.getId() + " was updated");
    }

    public void deleteAd(Ad ad) {
        adDao.delete(ad);
        LOGGER.info(() -> " ad with id: " + ad.getId() + " was deleted");
    }

    public List<Ad> getAllAds() {
        List<Ad> ads = adDao.findAll(Ad.class);
        LOGGER.info(() -> "all ad have gotten from DB");
        return ads;
    }

    public void writeAdsToCsvFromDb() {
        csvWriter.writeToCsvFile(getAllAds());
        LOGGER.info(() -> "all users saved to CSV");
    }

    public void readAdsFromCsvToDb() {
        List<Ad> ads = (List<Ad>) csvReader.readerFromCsv(Ad.class);
        for (Object ad : ads) {
            adDao.saveOrUpdate(ad);
        }
        LOGGER.info(() -> "all users saved to DB");
    }
}
