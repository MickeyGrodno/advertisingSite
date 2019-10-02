package ru.senla.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class CommentDto {
    @ApiModelProperty(notes = "id of comment", name = "id", example = "6")
    private Long id;
    @ApiModelProperty(notes = "id of comment user", name = "userId", example = "10")
    private Long userId;
    @ApiModelProperty(notes = "Comment creation date in millis", name = "commentDate", example = "1569801500000", required = true)
    private Date commentDate;
    @ApiModelProperty(notes = "Comment message text", name = "commentMessage", example = "The price is too high")
    private String commentMessage;
    @ApiModelProperty(notes = "id of ad", name = "adId", example = "12", required = true)
    private Long adId;

    public Long getAdId() {
        return adId;
    }

    public void setAdId(Long adId) {
        this.adId = adId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentMessage() {
        return commentMessage;
    }

    public void setCommentMessage(String commentMessage) {
        this.commentMessage = commentMessage;
    }
}
