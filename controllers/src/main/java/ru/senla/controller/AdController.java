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
import ru.senla.dto.AdDto;
import ru.senla.service.AdService;

import java.util.List;

@RestController
@RequestMapping(value = "/ad")
public class AdController {

    @Autowired
    AdService adService;

    @GetMapping(value = "/all")
    public List<AdDto> getAllAds() {
        List<AdDto> adDtoList = adService.getAllAds();
        return adDtoList;
    }

    @GetMapping("/{id}")
    public AdDto getAd(@PathVariable Long id) {
        AdDto adDto = adService.getAdById(id);
        return adDto;
    }

    @PostMapping
    public Long saveAd(@RequestBody AdDto adDto) {
        Long id = adService.saveAd(adDto);
        return id;
    }

    @PutMapping
    public void updateAd(@RequestBody AdDto adDto) {
        adService.updateAd(adDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAd(@PathVariable(name = "id") Long idAd) {
        adService.deleteAd(idAd);
    }

    @GetMapping("/user_id/{id}")
    public List<AdDto> getAdsByUserId(@PathVariable Long id) {
        List<AdDto> adDtoList = adService.getAdsByUserId(id);
        return adDtoList;
    }

    @GetMapping("/user_login/{login}")
    public List<AdDto> searchAdByUserLogin(@PathVariable String login) {
        List<AdDto> adDtoList = adService.searchAdByUserLogin(login);
        return adDtoList;
    }

    @GetMapping("/ad_type/{adTypeId}")
    public List<AdDto> searchAdByAdType(@PathVariable Long adTypeId) {
        List<AdDto> adDtoList = adService.searchAdByAdType(adTypeId);
        return adDtoList;
    }

    @GetMapping("/message_text/{text}")
    public List<AdDto> searchByAdMessageText(@PathVariable String text) {
        List<AdDto> adDtoList = adService.searchByAdMessageText(text);
        return adDtoList;
    }
}