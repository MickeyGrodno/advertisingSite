package ru.senla.daoImpl.entityDaoImpl;

import org.springframework.stereotype.Repository;
import ru.senla.dao.entityDao.CommentDao;
import ru.senla.daoImpl.AbstractDaoImpl;
import ru.senla.entity.Comment;

@Repository
public class CommentDaoImpl extends AbstractDaoImpl implements CommentDao {

    public CommentDaoImpl() {
        super(Comment.class);
    }

}
