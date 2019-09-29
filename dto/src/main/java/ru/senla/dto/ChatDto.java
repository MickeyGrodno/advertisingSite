package ru.senla.dto;

import org.apache.logging.log4j.message.Message;

import java.util.List;

public class ChatDto {
    private Long id;
    private String chatName;
    private List<UserDto> userDtoList;
    private List<MessageDto> messageDtoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public List<UserDto> getUserDtoList() {
        return userDtoList;
    }

    public void setUserDtoList(List<UserDto> userDtoList) {
        this.userDtoList = userDtoList;
    }

    public List<MessageDto> getMessageDtoList() {
        return messageDtoList;
    }

    public void setMessageDtoList(List<MessageDto> messageDtoList) {
        this.messageDtoList = messageDtoList;
    }
}
