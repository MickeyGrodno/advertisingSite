package ru.senla.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class MessageDto {
    @ApiModelProperty(notes = "id of message", name = "id", example = "7")
    private Long id;
    @ApiModelProperty(notes = "id of chat", name = "chatId", example = "5", required = true)
    private Long chatId;
    @ApiModelProperty(notes = "User of message", name = "userDto", example = "7" ,required = true)
    private UserDto userDto;
    @ApiModelProperty(notes = "Text of message", name = "text", example = "Hello!", required = true)
    private String text;
    @ApiModelProperty(notes = "Message creation date in millis", name = "messageDate", example = "1569801500000", required = true)
    private Date messageDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }
}
