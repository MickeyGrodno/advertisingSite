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
import ru.senla.dto.AdDto;
import ru.senla.dto.AdTypeDto;
import ru.senla.entity.Ad;
import ru.senla.entity.AdType;
import ru.senla.service.AdService;
import ru.senla.service.EntityToDtoConverter;

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
    private final EntityToDtoConverter entityToDtoConverter;

    @Autowired
    public AdServiceImpl(AdDao adDao, CsvWriter csvWriter, CsvReader csvReader, UserDao userDao,
                         CredentialDao credentialDao, AdTypeDao adTypeDao, EntityToDtoConverter entityToDtoConverter) {
        this.adDao = adDao;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
        this.userDao = userDao;
        this.credentialDao = credentialDao;
        this.adTypeDao = adTypeDao;
        this.entityToDtoConverter = entityToDtoConverter;
    }


    public AdDto getAdById(Long id) {
        Ad ad = (Ad) adDao.read(id);
        AdDto adDto = entityToDtoConverter.adToAdDto(ad);
        LOGGER.info(() -> " ad with id: " + ad.getId() + "has gotten from DB");
        return adDto;
    }

    public Long saveAd(AdDto adDto) {
        Ad ad = entityToDtoConverter.adDtoToAd(adDto);
        Long id = (Long) adDao.create(ad);
        LOGGER.info(() -> " ad with id: " + id + "saved in DB");
        return id;
    }

    public void updateAd(AdDto adDto) {
        Ad ad = entityToDtoConverter.adDtoToAd(adDto);
        adDao.update(ad);
        LOGGER.info(() -> " ad with id: " + ad.getId() + " was updated");
    }

    public void deleteAd(Long adId) {
        Ad ad = (Ad) adDao.read(adId);
        adDao.delete(ad);
        LOGGER.info(() -> " ad with id: " + adId + " was deleted");
    }

    public List<AdDto> getAllAds() {
        List<Ad> ads = adDao.findAll(Ad.class);
        List<AdDto> adsDto = entityToDtoConverter.adListToAdDtoList(ads);
        LOGGER.info(() -> "all ad have gotten from DB");
        return adsDto;
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

    public List<AdDto> getAdsByUserId(Long userId) {
        List<Ad> ads = adDao.getByUserId(userId);
        List<AdDto> adDtoList = entityToDtoConverter.adListToAdDtoList(ads);
        LOGGER.info(() -> "All user ads with id " + userId + "are obtained from the DB");
        return adDtoList;
    }

    public List<AdDto> searchByAdMessageText(String text) {
        List<Ad> ads = adDao.searchByText(text);
        List<AdDto> adDtoList = entityToDtoConverter.adListToAdDtoList(ads);
        if (adDtoList.size() == 0) {
            LOGGER.info(() -> " No results for \"" + text + "\".");
        } else {
            LOGGER.info(() -> " Ads with \"" + text + "\"has gotten from DB.");
        }
        return adDtoList;
    }

    public List<AdDto> searchAdByAdType(Long adTypeId) {
        AdType adType = (AdType) adTypeDao.read(adTypeId);
        List<Ad> ads = adDao.searchByAdType(adType);
        List<AdDto> adDtoList = entityToDtoConverter.adListToAdDtoList(ads);
        LOGGER.info(() -> " Ads with adType has gotten from DB.");
        return adDtoList;
    }

    public List<AdDto> searchAdByUserLogin(String login) {
        List<Ad> ads = adDao.searchByUserLogin(login);
        List<AdDto> adDtoList = entityToDtoConverter.adListToAdDtoList(ads);
        LOGGER.info(() -> " Ads with login has gotten from DB.");
        return adDtoList;
    }
}
