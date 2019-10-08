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
import ru.senla.dto.AdDto;
import ru.senla.service.AdService;

import java.util.List;

@RestController
@EnableResourceServer
@RequestMapping(value = "/ad")
public class AdController {

    @Autowired
    AdService adService;

    @GetMapping(value = "/public/all")
    public List<AdDto> getAllAds() {
        List<AdDto> adDtoList = adService.getAllAds();
        return adDtoList;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public AdDto getAd(@PathVariable Long id) {
        AdDto adDto = adService.getAdById(id);
        return adDto;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public Long saveAd(@RequestBody AdDto adDto) {
        Long id = adService.saveAd(adDto);
        return id;
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public void updateAd(@RequestBody AdDto adDto) {
        adService.updateAd(adDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public void deleteAd(@PathVariable(name = "id") Long idAd) {
        adService.deleteAd(idAd);
    }

    @GetMapping("/public/user_id/{id}")
    public List<AdDto> getAdsByUserId(@PathVariable Long id) {
        List<AdDto> adDtoList = adService.getAdsByUserId(id);
        return adDtoList;
    }

    @GetMapping("/public/user_login/{login}")
    public List<AdDto> searchAdByUserLogin(@PathVariable String login) {
        List<AdDto> adDtoList = adService.searchAdByUserLogin(login);
        return adDtoList;
    }

    @GetMapping("/public/ad_type/{adTypeId}")
    public List<AdDto> searchAdByAdType(@PathVariable Long adTypeId) {
        List<AdDto> adDtoList = adService.searchAdByAdType(adTypeId);
        return adDtoList;
    }

    @GetMapping("/public/message_text/{text}")
    public List<AdDto> searchByAdMessageText(@PathVariable String text) {
        List<AdDto> adDtoList = adService.searchByAdMessageText(text);
        return adDtoList;
    }
}
