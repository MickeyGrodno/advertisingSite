package ru.senla.service;

import ru.senla.entity.Comment;

import java.util.List;

public interface CommentService {

    Comment getCommentById(Long id);

    Long saveComment(Comment comment);

    void updateComment(Comment comment);

    void deleteComment(Comment comment);

    List getAllComments();

    void writeCommentsToCsvFromDb();

    void readCommentsFromCsvToDb();
}
