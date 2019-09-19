package ru.senla.service;

import ru.senla.entity.Ad;

import java.util.List;

public interface AdService {

    Ad getAdById(Long id);

    Long saveAd(Ad ad);

    void updateAd(Ad ad);

    void deleteAd(Ad ad);

    List<Ad> getAllAds();

    void writeAdsToCsvFromDb();

    void readAdsFromCsvToDb();
}
