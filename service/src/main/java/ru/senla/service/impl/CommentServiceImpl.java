package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.senla.dao.entityDao.AdDao;
import ru.senla.dao.entityDao.CommentDao;
import ru.senla.dao.entityDao.UserDao;
import ru.senla.dto.CommentDto;
import ru.senla.entity.Ad;
import ru.senla.entity.Comment;
import ru.senla.entity.User;
import ru.senla.service.CommentService;
import ru.senla.service.EntityToDtoConverter;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER = LogManager.getLogger(ChatServiceImpl.class.getName());
    private final CommentDao commentDao;
    private final EntityToDtoConverter entityToDtoConverter;
    private final AdDao adDao;
    private final UserDao userDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao,
                              EntityToDtoConverter entityToDtoConverter, AdDao adDao, UserDao userDao) {
        this.commentDao = commentDao;
        this.entityToDtoConverter = entityToDtoConverter;
        this.adDao = adDao;
        this.userDao = userDao;
    }

    public CommentDto getCommentById(Long id) {
        Comment comment = (Comment) commentDao.read(id);
        CommentDto commentDto = entityToDtoConverter.commentToCommentDto(comment);
        LOGGER.info(() -> " comment with id: " + comment.getId() + "has gotten from DB");
        return commentDto;
    }

    public Long saveComment(CommentDto commentDto) {
        Comment comment = entityToDtoConverter.commentDtoToComment(commentDto);
        if (commentDto.getAdId() != null) {
            Ad ad = (Ad) adDao.load(commentDto.getAdId());
            ad.getCommentsList().add(comment);
            comment.setAd(ad);
        }
        if (commentDto.getUserId() != null) {
            User user = (User) userDao.read(commentDto.getUserId());
            user.getCommentList().add(comment);
            comment.setUser(user);
        }
        Long id = (Long) commentDao.create(comment);
        LOGGER.info(() -> " comment with id: " + id + "saved in DB");
        return id;
    }

    public void updateComment(CommentDto commentDto) {
        Comment comment = entityToDtoConverter.commentDtoToComment(commentDto);
        commentDao.update(comment);
        LOGGER.info(() -> " Comment with id: " + comment.getId() + " was updated");
    }

    public void deleteComment(Long id) {
        Comment comment = (Comment) commentDao.load(id);
        commentDao.delete(comment);
        LOGGER.info(() -> " Comment with id: " + comment.getId() + " was deleted");
    }

    public List<CommentDto> getAllComments() {
        List<Comment> ads = commentDao.findAll(Comment.class);
        List<CommentDto> commentDtoList = entityToDtoConverter.commentListToCommentDtoList(ads);
        LOGGER.info(() -> "all comments have gotten from DB");
        return commentDtoList;
    }
}
