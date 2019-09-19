package ru.senla.service;

import ru.senla.entity.AdType;

import java.util.List;

public interface AdTypeService {

    AdType getAdTypeById(Long id);

    Long saveAdType(AdType adType);

    void updateAdType(AdType adType);

    void deleteAdType(AdType adType);

    List getAllAdTypes();

    void writeAdTypesToCsvFromDb();

    void readAdTypesFromCsvToDb();
}
