package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reflection.interfaces.CsvReader;
import reflection.interfaces.CsvWriter;
import ru.senla.dao.entityDao.AdTypeDao;
import ru.senla.dto.AdTypeDto;
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
    private final EntityToDtoConverterImpl entityToDtoConverter;

    @Autowired
    public AdTypeServiceImpl(AdTypeDao adTypeDao, CsvWriter csvWriter, CsvReader csvReader,
                             EntityToDtoConverterImpl entityToDtoConverter) {
        this.adTypeDao = adTypeDao;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
        this.entityToDtoConverter = entityToDtoConverter;
    }

    public AdTypeDto getAdTypeById(Long id) {
        AdType adType = (AdType) adTypeDao.read(id);
        AdTypeDto adTypeDto = entityToDtoConverter.adTypeToAdTypeDto(adType);
        LOGGER.info(() -> " adType with id: " + adType.getId() + "has gotten from DB");
        return adTypeDto;
    }

    public Long saveAdType(AdTypeDto adTypeDto) {
        AdType adType = entityToDtoConverter.adTypeDtoToAdType(adTypeDto);
        Long id = (Long) adTypeDao.create(adType);
        LOGGER.info(() -> " adType with id: " + id + "saved in DB");
        return id;
    }

    public void updateAdType(AdTypeDto adTypeDto) {
        AdType adType = entityToDtoConverter.adTypeDtoToAdType(adTypeDto);
        adTypeDao.update(adType);
        LOGGER.info(() -> " adType with id: " + adType.getId() + " was updated");
    }

    public void deleteAdType(Long id) {
        AdType adType = (AdType) adTypeDao.read(id);
        adTypeDao.delete(adType);
        LOGGER.info(() -> " adType with id: " + id + " was deleted");
    }

    public List getAllAdTypes() {
        List<AdType> ads = adTypeDao.findAll(AdType.class);
        List<AdTypeDto> adTypeDtoList = entityToDtoConverter.adTypeListToAdTypeDtoList(ads);
        LOGGER.info(() -> "all adTypes have gotten from DB");
        return adTypeDtoList;
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
