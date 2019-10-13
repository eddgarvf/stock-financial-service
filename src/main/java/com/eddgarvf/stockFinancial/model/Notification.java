package com.eddgarvf.stockFinancial.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "notification")
public class Notification {

    @Id
    @GeneratedValue
    int id;
    String message;
    Date date;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    User user;
}
