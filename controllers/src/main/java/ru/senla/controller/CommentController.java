package ru.senla.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
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
@EnableResourceServer
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    CommentService commentService;


    @GetMapping(value = "/public/all")
    public List<CommentDto> getAlMessages() {
        List<CommentDto> commentDtoList = commentService.getAllComments();
        return commentDtoList;
    }

    @GetMapping(value = "/public/{id}")
    public CommentDto getById(@PathVariable Long id) {
        CommentDto commentDto = commentService.getCommentById(id);
        return commentDto;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public Long createComment(@RequestBody CommentDto commentDto) {
        Long id = commentService.saveComment(commentDto);
        return id;
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public void updateComment(@RequestBody CommentDto commentDto) {
        commentService.updateComment(commentDto);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public void deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
