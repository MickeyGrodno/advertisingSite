package ru.senla.dto;

import io.swagger.annotations.ApiModelProperty;

public class CredentialDto {
    @ApiModelProperty(notes = "id of credential", name = "credentialId", example = "7")
    private Long credentialId;
    @ApiModelProperty(notes = "Login of user", name = "login", example = "Sergei_777", required = true)
    private String login;
    @ApiModelProperty(notes = "Password of user", name = "password", example = "Mypassword1990", required = true)
    private String password;
    @ApiModelProperty(notes = "Email of user", name = "email", example = "sergei199@mail.ru", required = true)
    private String email;
    @ApiModelProperty(notes = "Role of user(administrator, moderator and others)", name = "role", example = "administrator",
            required = true)
    private String role;
    @ApiModelProperty(notes = "id of user", name = "userId", example = "7")
    private Long userId;

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
