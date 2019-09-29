package ru.senla.dto;

import java.util.Date;
import java.util.List;

public class AdDto {
    private Long id;
    private UserDto userDto;
    private AdTypeDto adTypeDto;
    private String adMessage;
    private Date adDate;
    private Date adTopDate;
    private List<CommentDto> commentDtoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDto userDto) {
        this.userDto = userDto;
    }

    public AdTypeDto getAdTypeDto() {
        return adTypeDto;
    }

    public void setAdTypeDto(AdTypeDto adTypeDto) {
        this.adTypeDto = adTypeDto;
    }

    public String getAdMessage() {
        return adMessage;
    }

    public void setAdMessage(String adMessage) {
        this.adMessage = adMessage;
    }

    public Date getAdDate() {
        return adDate;
    }

    public void setAdDate(Date adDate) {
        this.adDate = adDate;
    }

    public Date getAdTopDate() {
        return adTopDate;
    }

    public void setAdTopDate(Date adTopDate) {
        this.adTopDate = adTopDate;
    }

    public List<CommentDto> getCommentDtoList() {
        return commentDtoList;
    }

    public void setCommentDtoList(List<CommentDto> commentDtoList) {
        this.commentDtoList = commentDtoList;
    }
}
