package ru.senla.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.senla.dto.CommentDto;
import ru.senla.service.CommentService;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping(value = "/all")
    public List<CommentDto> getAlMessages() {
        List<CommentDto> commentDtoList = commentService.getAllComments();
        return commentDtoList;
    }

    @GetMapping(value = "/{id}")
    public CommentDto getById(@PathVariable Long id) {
        CommentDto commentDto = commentService.getCommentById(id);
        return commentDto;
    }

    @PostMapping
    public Long createComment(@RequestBody CommentDto commentDto) {
        Long id = commentService.saveComment(commentDto);
        return id;
    }

    @PutMapping
    public void updateComment(@RequestBody CommentDto commentDto) {
        commentService.updateComment(commentDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
