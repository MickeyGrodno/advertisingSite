package ru.senla.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "credentials")
public class Credential implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "credential_id", unique = true, nullable = false)
    private Long credentialId;

    @Column(name = "login", unique = true, nullable = false, length = 45)
    private String login;

    @Column(name = "password", nullable = false, length = 45)
    private String password;

    @Column(name = "email", unique = true, nullable = false, length = 45)
    private String email;

    @Column(name = "role", nullable = false, length = 45)
    private String role;

    @OneToOne(mappedBy = "credential", cascade = javax.persistence.CascadeType.ALL)
    private User user;

    public Credential() {
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long userId) {
        this.credentialId = userId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
