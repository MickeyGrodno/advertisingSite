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
import ru.senla.dto.ChatDto;
import ru.senla.service.ChatService;

import java.util.List;

@RestController
@RequestMapping(value = "/chat")

public class ChatController {
    @Autowired
    ChatService chatService;

    @GetMapping(value = "/all")
    public List<ChatDto> getAllChats() {
        List<ChatDto> chatDtoList = chatService.getAllChats();
        return chatDtoList;
    }

    @GetMapping("/{id}")
    public ChatDto getChatTypeById(@PathVariable Long id) {
        ChatDto chatDto = chatService.getChatById(id);
        return chatDto;
    }

    @PostMapping
    public Long saveChat(@RequestBody ChatDto chatDto) {
        Long id = chatService.saveChat(chatDto);
        return id;
    }

    @PutMapping
    public void updateChat(@RequestBody ChatDto chatDto) {
        chatService.updateChat(chatDto);
    }

    @DeleteMapping("/{id}")
    public void deleteAdType(@PathVariable Long id) {
        chatService.deleteChat(id);
    }
}
