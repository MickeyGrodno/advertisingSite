package ru.senla.service;

import ru.senla.dto.UserDto;
import ru.senla.entity.User;
import java.util.List;

public interface UserService {

    UserDto getUserById(Long id);

    Long saveUser(UserDto userDto);

    void updateUser(UserDto userDto);

    void deleteUser(Long userId);

    List getAllUsers();

    void writeUsersToCsvFromDb();

    void readUsersFromCsvToDb();
}
