package ru.senla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.senla.dto.AdTypeDto;
import ru.senla.service.AdTypeService;

import java.util.List;

@RestController
@RequestMapping(value = "/ad_type")

public class AdTypeController {

    @Autowired
    AdTypeService adTypeService;

    @GetMapping("/all")
    public List<AdTypeDto> getAllAdTypes() {
        List<AdTypeDto> adTypeDtoList = adTypeService.getAllAdTypes();
        return adTypeDtoList;
    }

    @GetMapping("/{id}")
    public AdTypeDto getAdTypeById(@PathVariable Long id) {
        AdTypeDto adTypeDto = adTypeService.getAdTypeById(id);
        return adTypeDto;
    }

    @PostMapping
    public Long saveAdType(@RequestBody AdTypeDto adTypeDto) {
        Long id = adTypeService.saveAdType(adTypeDto);
        return id;
    }

    @PutMapping
    public void updateAdType(@RequestBody AdTypeDto adTypeDto) {
        adTypeService.updateAdType(adTypeDto);
    }
    
    @DeleteMapping("/{id}")
    public void deleteAdType(@PathVariable Long id) {
        adTypeService.deleteAdType(id);
    }

}
