package ru.senla.dto;

import io.swagger.annotations.ApiModelProperty;
import org.apache.logging.log4j.message.Message;

import java.util.List;

public class ChatDto {

    @ApiModelProperty(notes = "id of Chat", name = "id", example = "12")
    private Long id;
    @ApiModelProperty(example = "First chat", required = true)
    private String chatName;
    @ApiModelProperty(notes = "List of users")
    private List<UserDto> userDtoList;
    @ApiModelProperty(notes = "List of messages")
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
