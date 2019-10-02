package ru.senla.service;

import ru.senla.dto.CommentDto;
import ru.senla.entity.Comment;

import java.util.List;

public interface CommentService {

    CommentDto getCommentById(Long id);

    Long saveComment(CommentDto commentDto);

    void updateComment(CommentDto commentDto);

    void deleteComment(Long id);

    List<CommentDto> getAllComments();

}
