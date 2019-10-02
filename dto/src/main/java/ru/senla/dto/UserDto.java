package ru.senla.dto;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class UserDto {

    @ApiModelProperty(notes = "id of User", name = "id", value = "11111")
    private Long id;
    @ApiModelProperty(notes = "name of User", name = "name", required = true, value = "Sergei")
    private String firstName;
    @ApiModelProperty(notes = "last name of User", name = "last name", value = "Shabalin")
    private String lastName;
    @ApiModelProperty(notes = "user sex(true = male, false = female) ", required = true, name = "sex", value = "true", example = "false")
    private boolean gender;
    @ApiModelProperty(notes = "user birth date in millis", required = true, name = "birthDate", example = "1569801600000")
    private Date birthDate;
    @ApiModelProperty(notes = "user rating", name = "rating", required = true, example = "5")
    private int userRating;
    @ApiModelProperty(notes = "credential Id", name = "credentialId", required = true, example = "2")
    private Long credentialId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getUserRating() {
        return userRating;
    }

    public void setUserRating(int userRating) {
        this.userRating = userRating;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }
}
