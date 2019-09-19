package ru.senla.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "ad")
public class Ad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ad_id", unique = true, nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinColumn(name = "ad_type_id")
    private AdType adType;

    @Column(name = "ad_message", nullable = false, length = 45)
    private String adMessage;

    @Temporal(TemporalType.DATE)
    @Column(name = "ad_date", nullable = false)
    private Date adDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "ad_top_date")
    private Date adTopDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ad")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, CascadeType.DELETE})
    private List<Comment> commentsList;

    public Ad() {
    }

    public Ad(User user, AdType adType, String adMessage, Date adDate, Date adTopDate) {
        this.user = user;
        this.adType = adType;
        this.adMessage = adMessage;
        this.adDate = adDate;
        this.adTopDate = adTopDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AdType getAdType() {
        return adType;
    }

    public void setAdType(AdType adType) {
        this.adType = adType;
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

    public List<Comment> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comment> commentsList) {
        this.commentsList = commentsList;
    }
}
