package ru.senla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.senla.dto.UserDto;
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
}
