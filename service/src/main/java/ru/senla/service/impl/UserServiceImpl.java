package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.senla.dao.entityDao.CredentialDao;
import ru.senla.dao.entityDao.UserDao;
import ru.senla.dto.UserDto;
import ru.senla.entity.Credential;
import ru.senla.entity.User;
import ru.senla.service.EntityToDtoConverter;
import ru.senla.service.UserService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class.getName());
    private final UserDao userDao;
    private final EntityToDtoConverter entityToDtoMapper;
    private final CredentialDao credentialDao;
    private final String userId = "user with id: ";

    @Autowired
    public UserServiceImpl(UserDao userDao, EntityToDtoConverter entityToDtoMapper,
                           CredentialDao credentialDao) {
        this.userDao = userDao;
        this.entityToDtoMapper = entityToDtoMapper;
        this.credentialDao = credentialDao;
    }

    public UserDto getUserById(Long id) {
        User user = (User) userDao.read(id);
        UserDto userDto = entityToDtoMapper.userToUserDto(user);
        LOGGER.info(() -> userId + user.getId() + "has gotten from DB");
        return userDto;
    }

    public Long saveUser(UserDto userDto) {
        User user = entityToDtoMapper.userDtoToUser(userDto);
        if (userDto.getCredentialId() != null) {
            Credential credential = (Credential) credentialDao.load(userDto.getCredentialId());
            credential.setUser(user);
            user.setCredential(credential);
        }
        Long id = (Long) userDao.create(user);
        LOGGER.info(() -> userId + id + "saved in DB");
        return id;
    }

    public void updateUser(UserDto userDto) {
        User user = entityToDtoMapper.userDtoToUser(userDto);
        userDao.update(user);
        LOGGER.info(() -> userId + user.getId() + " was updated");
    }

    public void deleteUser(Long userId) {
        User user = (User) userDao.load(userId);
        userDao.delete(user);
        LOGGER.info(() -> userId + user.getId() + " was updated");
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
}
