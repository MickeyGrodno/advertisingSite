package ru.senla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.senla.dto.MessageDto;
import ru.senla.service.MessageService;

import java.util.List;

@RestController
@RequestMapping(value = "/message")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping(value = "/all")
    public List<MessageDto> getAllMessages() {
        List<MessageDto> messageDtoList = messageService.getAllMessages();
        return messageDtoList;
    }

    @GetMapping(value = "/{id}")
    public MessageDto getById(@PathVariable Long id) {
        MessageDto messageDto = messageService.getMessageById(id);
        return messageDto;
    }

    @PostMapping
    public Long createMessage(@RequestBody MessageDto messageDto) {
        Long id = messageService.saveMessage(messageDto);
        return id;
    }

    @PutMapping
    public void updateMessage(@RequestBody MessageDto messageDto) {
        messageService.updateMessage(messageDto);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }

    @GetMapping(value = "/chat_name/{chatName}")
    public List<MessageDto> getAllMessagesByChatName(@PathVariable String chatName) {
        List<MessageDto> messageDtoList = messageService.getAllMessagesByChatName(chatName);
        return messageDtoList;
    }
}
