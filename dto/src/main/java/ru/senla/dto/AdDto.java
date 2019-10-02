package ru.senla.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

public class AdDto {
    @ApiModelProperty(notes = "id of Ad", name = "id", example = "10")
    private Long id;
    @ApiModelProperty(notes = "User of Ad", name = "userDto", value = "UserDto", required = true)
    private UserDto userDto;
    @ApiModelProperty(notes = "Ad type of Ad", name = "adTypeDto", value = "AdTypeDto", required = true)
    private AdTypeDto adTypeDto;
    @ApiModelProperty(notes = "Message text of Ad", name = "adMessage", value = "String", example = "Volkswagen passat, 2001",
            required = true)
    private String adMessage;
    @ApiModelProperty(notes = "Ad creation date in millis", name = "adDate", example = "1569801500000", required = true)
    private Date adDate;
    @ApiModelProperty(notes = "Date of rise in top ad", name = "adTopDate", example = "1569801600000")
    private Date adTopDate;
    @ApiModelProperty(notes = "List of comments", name = "commentDtoList")
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
