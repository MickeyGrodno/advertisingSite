package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reflection.interfaces.CsvReader;
import reflection.interfaces.CsvWriter;
import ru.senla.dao.entityDao.AdDao;
import ru.senla.dao.entityDao.AdTypeDao;
import ru.senla.dao.entityDao.CredentialDao;
import ru.senla.dao.entityDao.UserDao;
import ru.senla.entity.Ad;
import ru.senla.entity.AdType;
import ru.senla.entity.Credential;
import ru.senla.entity.User;
import ru.senla.service.AdService;

import java.util.List;

@Service
@Transactional
public class AdServiceImpl implements AdService {

    private static final Logger LOGGER = LogManager.getLogger(AdServiceImpl.class.getName());
    private final AdDao adDao;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;
    private final UserDao userDao;
    private final CredentialDao credentialDao;
    private final AdTypeDao adTypeDao;

    @Autowired
    public AdServiceImpl(AdDao adDao, CsvWriter csvWriter, CsvReader csvReader, UserDao userDao, CredentialDao credentialDao, AdTypeDao adTypeDao) {
        this.adDao = adDao;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
        this.userDao = userDao;
        this.credentialDao = credentialDao;
        this.adTypeDao = adTypeDao;
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

    public List<Ad> getAdsByUserId(Long userId) {
        User user = (User) userDao.read(userId);
        return user.getAdList();
    }

    public List<Ad> searchByAdMessageText(String text) {
        List<Ad> ads = adDao.searchByText(text);
        if (ads == null) {
            LOGGER.info(() -> " No results for \"" + text + "\".");
        } else {
            LOGGER.info(() -> " Ads with \"" + text + "\"has gotten from DB.");
        }
        return ads;
    }

    public List<Ad> searchAdByAdType(AdType adType) {
        AdType currentAdType = adTypeDao.getAdTypeByCurrentAdType(adType);
        LOGGER.info(() -> " Ads with adType has gotten from DB.");
        List<Ad> adList = currentAdType.getAdList();
        adList.get(0).getAdMessage();
        return adList;
    }

    public List<Ad> searchAdByUserLogin(String login) {
        Credential credential = credentialDao.getCredentialByLogin(login);
        User user = credential.getUser();
        LOGGER.info(() -> " Ads with login has gotten from DB.");

        return user.getAdList();
    }
}
