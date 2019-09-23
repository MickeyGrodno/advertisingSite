package ru.senla.entity;

import reflection.PropertyType;
import reflection.annotations.CsvEntity;
import reflection.annotations.CsvProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@CsvEntity(fileName = "credentials.csv")
@Entity
@Table(name = "credentials")
public class Credential implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "credential_id", unique = true, nullable = false)
    @CsvProperty(propertyType = PropertyType.SIMPLE_PROPERTY, columnNumber = 1)
    Long credentialId;

    @Column(name = "login", unique = true, nullable = false, length = 45)
    @CsvProperty(propertyType = PropertyType.SIMPLE_PROPERTY, columnNumber = 2)
    String login;

    @Column(name = "password", nullable = false, length = 45)
    @CsvProperty(propertyType = PropertyType.SIMPLE_PROPERTY, columnNumber = 3)
    String password;

    @Column(name = "email", unique = true, nullable = false, length = 45)
    @CsvProperty(propertyType = PropertyType.SIMPLE_PROPERTY, columnNumber = 4)
    String email;

    @Column(name = "role", nullable = false, length = 45)
    @CsvProperty(propertyType = PropertyType.SIMPLE_PROPERTY, columnNumber = 5)
    String role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @CsvProperty(propertyType = PropertyType.COMPOSITE_PROPERTY, columnNumber = 6, keyField = "1")
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
