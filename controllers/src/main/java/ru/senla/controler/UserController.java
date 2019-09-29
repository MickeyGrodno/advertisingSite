package ru.senla.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.senla.dto.UserDto;
import ru.senla.entity.Ad;
import ru.senla.service.AdService;
import ru.senla.service.AdTypeService;
import ru.senla.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, value = "/all")
    public List<UserDto> getAllUsers() {
        List<UserDto> userDtos = userService.getAllUsers();
        return userDtos;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public UserDto getUser(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return userDto;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public Long createUser(@RequestBody UserDto userDto) {
        Long id = userService.saveUser(userDto);
        return id;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void updateUser(@RequestBody UserDto userDto) {
        userService.updateUser(userDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }



//    private AdService adService;
//    private AdTypeService adTypeService;
//
//    @Autowired
//    public void setAdTypeService(AdTypeService adTypeService) {
//        this.adTypeService = adTypeService;
//    }
//
//    @Autowired
//    public void setAdService(AdService adService) {
//        this.adService = adService;
//    }
//
//    @GetMapping("/getAdList")
//    public List<Ad>  getAdList(){
//        List<Ad> result = adService.getAllAds();
//        return result;
//    }
//    @GetMapping("/getAd/{id}")
//    public Ad getAd(@PathVariable Long id){
//        Ad ad = adService.getAdById(id);
//        return ad;
//    }
//
//    @PostMapping(value = "/addAd", consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    public Long getAd(@RequestBody Ad ad){
//
//         return adService.saveAd(ad);
//    }
}
