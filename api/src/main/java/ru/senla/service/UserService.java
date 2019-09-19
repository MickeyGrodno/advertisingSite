package ru.senla.service;

import ru.senla.entity.User;
import java.util.List;

public interface UserService {

    User getUserById(Long id);

    Long saveUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    List getAllUsers();

    void writeUsersToCsvFromDb();

    void readUsersFromCsvToDb();
}
