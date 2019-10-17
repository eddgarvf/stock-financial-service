package com.eddgarvf.stockFinancial.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification")
@NamedQuery(query = "SELECT n FROM Notification n WHERE n.user.id = :userId", name = "getAllNotificationsByUser")
@NamedQuery(query = "SELECT n FROM Notification n WHERE n.user.id = :userId AND n.datetime BETWEEN :startDate AND :endDate", name = "getAllNotificationsByUserDate")
@NamedQuery(query = "SELECT n FROM Notification n WHERE n.user.id = :userId AND n.seen = :seen", name = "getAllNotificationsByUserSeen")
@NamedQuery(query = "SELECT n FROM Notification n WHERE n.user.id = :userId AND n.seen = :seen  AND n.datetime BETWEEN :startDate AND :endDate", name = "getAllNotificationsByUserSeenDates")
public class Notification {

    @Id
    @GeneratedValue
    private int id;
    private String message;
    private Date datetime;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean seen;

    @PrePersist
    protected void onCreate() {
        this.datetime = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
