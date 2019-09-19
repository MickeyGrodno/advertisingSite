package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reflection.interfaces.CsvReader;
import reflection.interfaces.CsvWriter;
import ru.senla.dao.entityDao.UserDao;
import ru.senla.entity.User;
import ru.senla.service.UserService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class.getName());
    private final UserDao userDao;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;

    @Autowired
    public UserServiceImpl(UserDao userDao, CsvWriter csvWriter, CsvReader csvReader) {
        this.userDao = userDao;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
    }

    public User getUserById(Long id) {
        User user = (User) userDao.read(id);
        LOGGER.info(() -> " User with id: " + user.getId() + "has gotten from DB");
        return user;
    }

    public Long saveUser(User user) {
        Long id = (Long) userDao.create(user);
        LOGGER.info(() -> "User with id: " + id + "saved in DB");
        return id;
    }

    public void updateUser(User user) {
        userDao.update(user);
        LOGGER.info(() -> " User with id: " + user.getId() + " was updated");
    }

    public void deleteUser(User user) {
        userDao.delete(user);
        LOGGER.info(() -> " User with id: " + user.getId() + " was updated");
    }

    public List getAllUsers() {
        List users = userDao.findAll(User.class);
        LOGGER.info(() -> "all user have gotten from DB");
        return users;
    }

    public void writeUsersToCsvFromDb() {
        csvWriter.writeToCsvFile(getAllUsers());
        LOGGER.info(() -> "all users saved to CSV");
    }

    public void readUsersFromCsvToDb() {
        List<User> users = (List<User>) csvReader.readerFromCsv(User.class);
        for (User user : users) {
            userDao.saveOrUpdate(user);
        }
        LOGGER.info(() -> "all users saved to DB");
    }
}
