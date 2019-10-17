package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.Notification;

import java.util.Date;
import java.util.List;

public interface NotificationDao {

    List<Notification> getAllByUser(int userId);
    List<Notification> getAllByUserDate(int userId, Date startDate, Date endDate);
    List<Notification> getAllSeenByUser(int userId);
    List<Notification> getAllSeenByUserDate(int userId, Date startDate, Date endDate);
    List<Notification> getAllNotSeenByUser(int userId);
    List<Notification> getAllNotSeenByUserDate(int userId, Date startDate, Date endDate);
    Notification getById(int notificationId);
    void add(Notification notification);
    void update(Notification notification);
    void delete(int notification);
}
