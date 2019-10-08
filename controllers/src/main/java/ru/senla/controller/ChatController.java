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
import ru.senla.dto.ChatDto;
import ru.senla.service.ChatService;

import java.util.List;

@RestController
@EnableResourceServer
@RequestMapping(value = "/chat")

public class ChatController {
    @Autowired
    ChatService chatService;

    @GetMapping(value = "/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<ChatDto> getAllChats() {
        List<ChatDto> chatDtoList = chatService.getAllChats();
        return chatDtoList;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ChatDto getChatTypeById(@PathVariable Long id) {
        ChatDto chatDto = chatService.getChatById(id);
        return chatDto;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public Long saveChat(@RequestBody ChatDto chatDto) {
        Long id = chatService.saveChat(chatDto);
        return id;
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public void updateChat(@RequestBody ChatDto chatDto) {
        chatService.updateChat(chatDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public void deleteChat(@PathVariable Long id) {
        chatService.deleteChat(id);
    }
}
