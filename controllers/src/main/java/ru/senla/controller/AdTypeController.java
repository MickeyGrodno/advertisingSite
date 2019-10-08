package ru.senla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
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
@EnableResourceServer
@RequestMapping(value = "/ad_type")

public class AdTypeController {

    @Autowired
    AdTypeService adTypeService;

    @GetMapping("/public/all")
    public List<AdTypeDto> getAllAdTypes() {
        List<AdTypeDto> adTypeDtoList = adTypeService.getAllAdTypes();
        return adTypeDtoList;
    }

    @GetMapping("/public/{id}")
    public AdTypeDto getAdTypeById(@PathVariable Long id) {
        AdTypeDto adTypeDto = adTypeService.getAdTypeById(id);
        return adTypeDto;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Long saveAdType(@RequestBody AdTypeDto adTypeDto) {
        Long id = adTypeService.saveAdType(adTypeDto);
        return id;
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateAdType(@RequestBody AdTypeDto adTypeDto) {
        adTypeService.updateAdType(adTypeDto);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAdType(@PathVariable Long id) {
        adTypeService.deleteAdType(id);
    }

}
