package ru.senla.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import reflection.PropertyType;
import reflection.annotations.CsvEntity;
import reflection.annotations.CsvProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@CsvEntity(fileName = "users.csv")
@Entity
@Table(name = "user")

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", unique = true, nullable = false)
    @CsvProperty(propertyType = PropertyType.SIMPLE_PROPERTY, columnNumber = 1, keyField = "1")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 45)
    @CsvProperty(propertyType = PropertyType.SIMPLE_PROPERTY, columnNumber = 2)
    private String firstName;

    @Column(name = "last_name", length = 45)
    @CsvProperty(propertyType = PropertyType.SIMPLE_PROPERTY, columnNumber = 3)
    private String lastName;

    @Column(name = "gender", nullable = false)
    @CsvProperty(propertyType = PropertyType.SIMPLE_PROPERTY, columnNumber = 4)
    private boolean gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date", nullable = false)
    @CsvProperty(propertyType = PropertyType.SIMPLE_PROPERTY, columnNumber = 5)
    private Date birthDate;

    @Column(name = "user_rating", nullable = false)
    @CsvProperty(propertyType = PropertyType.SIMPLE_PROPERTY, columnNumber = 6)
    private int userRating;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    private List<Ad> adList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    private List<Comment> commentList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<Message> messageList;

    @ManyToMany(mappedBy = "userList")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE})
    private List<Chat> chatList;

    @OneToOne(mappedBy = "user")
    private Credential credential;

    public User(String firstName, boolean gender, Date birthDate, int userRating) {
        this.firstName = firstName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.userRating = userRating;
    }

    public User() {
    }

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

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public List<Chat> getChatList() {
        return chatList;
    }

    public void setChatList(List<Chat> chatList) {
        this.chatList = chatList;
    }

}
