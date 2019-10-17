package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.dao.NotificationDao;
import com.eddgarvf.stockFinancial.dao.StockDailyDao;
import com.eddgarvf.stockFinancial.dao.UserDao;
import com.eddgarvf.stockFinancial.model.Notification;
import com.eddgarvf.stockFinancial.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private final NotificationDao notificationDao;
    private final StockDailyDao stockDailyDao;
    private final UserDao userDao;

    @Autowired
    public NotificationService(NotificationDao notificationDao,
                               StockDailyDao stockDailyDao,
                               UserDao userDao){
        this.notificationDao = notificationDao;
        this.stockDailyDao = stockDailyDao;
        this.userDao = userDao;
    }

    public List<Notification> getAllByUser(int userId) {
        return notificationDao.getAllByUser(userId);
    }

    public List<Notification> getAllByUserDate(int userId, Date startDate, Date endDate) {
        return notificationDao.getAllByUserDate(userId, startDate, getDateWithMidNight(endDate));
    }

    public List<Notification> getAllSeenByUser(int userId) {
        return notificationDao.getAllSeenByUser(userId);
    }

    public List<Notification> getAllSeenByUserDate(int userId, Date startDate, Date endDate) {
        return notificationDao.getAllSeenByUserDate(userId, startDate, getDateWithMidNight(endDate));
    }

    public List<Notification> getAllNotSeenByUser(int userId) {
        return notificationDao.getAllNotSeenByUser(userId);
    }

    public List<Notification> getAllNotSeenByUserDate(int userId, Date startDate, Date endDate) {
        return notificationDao.getAllNotSeenByUserDate(userId, startDate, getDateWithMidNight(endDate));
    }

    public Notification getById(int notificationId) {
        return notificationDao.getById(notificationId);
    }

    public void addPriceNotification(double price, int userId, int stockId, Date startDate, Date endDate) {
        User user = userDao.getById(userId);
        stockDailyDao.getListByDates(stockId,startDate, getDateWithMidNight(endDate)).forEach( stock -> {
            if(stock.getPriceChange() == price){
                Notification notification = new Notification();
                notification.setMessage("The stock price: $" + price + " was reached at " + stock.getDatetime());
                notification.setSeen(false);
                notification.setUser(user);
                notificationDao.add(notification);
            }
        });
    }

    public void add(Notification notification) {
        notificationDao.add(notification);
    }

    public void update(Notification notification) {
        notificationDao.update(notification);
    }

    public void delete(int notification) {
        notificationDao.delete(notification);
    }

    private Date getDateWithMidNight(Date date) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
            String strDate = dateFormatter.format(date) + " 23:59:59";
            SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATETIME_FORMAT);
            date = dateTimeFormatter.parse(strDate);
        }catch (ParseException e){
            logger.error(e.getMessage());
        }
        return date;
    }
}