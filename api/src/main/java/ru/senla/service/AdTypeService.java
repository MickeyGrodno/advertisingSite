package ru.senla.service;

import ru.senla.dto.AdTypeDto;
import ru.senla.entity.AdType;

import java.util.List;

public interface AdTypeService {

    AdTypeDto getAdTypeById(Long id);

    Long saveAdType(AdTypeDto adTypeDto);

    void updateAdType(AdTypeDto adTypeDto);

    void deleteAdType(Long id);

    List<AdTypeDto> getAllAdTypes();
}
