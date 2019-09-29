package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reflection.interfaces.CsvReader;
import reflection.interfaces.CsvWriter;
import ru.senla.dao.entityDao.CredentialDao;
import ru.senla.dao.entityDao.UserDao;
import ru.senla.dto.UserDto;
import ru.senla.entity.User;
import ru.senla.service.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class.getName());
    private final UserDao userDao;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;
    private final EntityToDtoConverterImpl entityToDtoMapper;
    private final CredentialDao credentialDao;

    @Autowired
    public UserServiceImpl(UserDao userDao, CsvWriter csvWriter, CsvReader csvReader, EntityToDtoConverterImpl entityToDtoMapper,
                           CredentialDao credentialDao) {
        this.userDao = userDao;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
        this.entityToDtoMapper = entityToDtoMapper;
        this.credentialDao = credentialDao;
    }

    public UserDto getUserById(Long id) {
        User user = (User) userDao.read(id);
        UserDto userDto = entityToDtoMapper.userToUserDto(user);
        LOGGER.info(() -> " User with id: " + user.getId() + "has gotten from DB");
        return userDto;
    }

    public Long saveUser(UserDto userDto) {
        User user = entityToDtoMapper.userDtoToUser(userDto);
        Long id = (Long) userDao.create(user);
        credentialDao.addUserIdToCredential(userDto.getCredentialId(), id);
        LOGGER.info(() -> "User with id: " + id + "saved in DB");
        return id;
    }

    public void updateUser(UserDto userDto) {
        User user = entityToDtoMapper.userDtoToUser(userDto);
        user.setId(userDto.getId());
        userDao.update(user);
        LOGGER.info(() -> " User with id: " + user.getId() + " was updated");
    }

    public void deleteUser(Long userId) {
        User user = (User) userDao.read(userId);
        userDao.delete(user);
        LOGGER.info(() -> " User with id: " + user.getId() + " was updated");
    }

    public List<UserDto> getAllUsers() {
        List<User> users = userDao.findAll(User.class);
        LOGGER.info(() -> "all user have gotten from DB");
        List<UserDto> userDtos = new ArrayList<>();
        for(User user : users) {
            UserDto userDto = entityToDtoMapper.userToUserDto(user);
            userDtos.add(userDto);
        }
        return userDtos;
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
