package ru.senla.service;

import ru.senla.dto.AdDto;
import ru.senla.dto.AdTypeDto;
import ru.senla.entity.Ad;
import ru.senla.entity.AdType;

import java.util.List;

public interface AdService {

    AdDto getAdById(Long id);

    Long saveAd(AdDto ad);

    void updateAd(AdDto ad);

    void deleteAd(Long adId);

    List<AdDto> getAllAds();

    List<AdDto> getAdsByUserId(Long userId);

    List<AdDto> searchAdByUserLogin(String login);

    List<AdDto> searchAdByAdType(Long adTypeId);

    List<AdDto> searchByAdMessageText(String text);
}
