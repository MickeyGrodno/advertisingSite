package ru.senla.service;

import ru.senla.entity.Ad;
import ru.senla.entity.AdType;

import java.util.List;

public interface AdService {

    Ad getAdById(Long id);

    Long saveAd(Ad ad);

    void updateAd(Ad ad);

    void deleteAd(Ad ad);

    List<Ad> getAllAds();

    void writeAdsToCsvFromDb();

    void readAdsFromCsvToDb();

    List<Ad> getAdsByUserId(Long userId);

    List<Ad> searchAdByUserLogin(String login);

    List<Ad> searchAdByAdType(AdType adType);

    List<Ad> searchByAdMessageText(String text);
}
