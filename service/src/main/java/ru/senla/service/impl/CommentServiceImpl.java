package ru.senla.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reflection.interfaces.CsvReader;
import reflection.interfaces.CsvWriter;
import ru.senla.dao.entityDao.CommentDao;
import ru.senla.entity.Comment;
import ru.senla.service.CommentService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private static final Logger LOGGER = LogManager.getLogger(ChatServiceImpl.class.getName());
    private final CommentDao commentDao;
    private final CsvWriter csvWriter;
    private final CsvReader csvReader;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao, CsvWriter csvWriter, CsvReader csvReader) {
        this.commentDao = commentDao;
        this.csvWriter = csvWriter;
        this.csvReader = csvReader;
    }

    public Comment getCommentById(Long id) {
        Comment comment = (Comment) commentDao.read(id);
        LOGGER.info(() -> " comment with id: " + comment.getId() + "has gotten from DB");
        return comment;
    }

    public Long saveComment(Comment comment) {
        Long id = (Long) commentDao.create(comment);
        LOGGER.info(() -> " comment with id: " + id + "saved in DB");
        return id;
    }

    public void updateComment(Comment comment) {
        commentDao.update(comment);
        LOGGER.info(() -> " Comment with id: " + comment.getId() + " was updated");
    }

    public void deleteComment(Comment comment) {
        commentDao.delete(comment);
        LOGGER.info(() -> " Comment with id: " + comment.getId() + " was deleted");
    }

    public List getAllComments() {
        List ads = commentDao.findAll(Comment.class);
        LOGGER.info(() -> "all Comments have gotten from DB");
        return ads;
    }

    public void writeCommentsToCsvFromDb() {
        csvWriter.writeToCsvFile(getAllComments());
        LOGGER.info(() -> "all comments saved to CSV");
    }

    public void readCommentsFromCsvToDb() {
        List<Comment> comments = (List<Comment>) csvReader.readerFromCsv(Comment.class);
        for (Object comment : comments) {
            commentDao.saveOrUpdate(comment);
        }
        LOGGER.info(() -> "all comments saved to DB");
    }
}
