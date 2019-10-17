package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.dao.NotificationDao;
import com.eddgarvf.stockFinancial.dao.StockDailyDao;
import com.eddgarvf.stockFinancial.dao.UserDao;
import com.eddgarvf.stockFinancial.model.Notification;
import com.eddgarvf.stockFinancial.model.User;
import com.eddgarvf.stockFinancial.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationDao notificationDao;
    private final StockDailyDao stockDailyDao;
    private final UserDao userDao;
    private final ServiceUtil serviceUtil;

    @Autowired
    public NotificationService(NotificationDao notificationDao,
                               StockDailyDao stockDailyDao,
                               UserDao userDao,
                               ServiceUtil serviceUtil){
        this.notificationDao = notificationDao;
        this.stockDailyDao = stockDailyDao;
        this.userDao = userDao;
        this.serviceUtil = serviceUtil;
    }

    public List<Notification> getAllByUser(int userId) {
        try{
            return notificationDao.getAllByUser(userId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Notification> getAllByUserDate(int userId, Date startDate, Date endDate) {
        try{
            return notificationDao.getAllByUserDate(userId, startDate, serviceUtil.getDateWithMidNight(endDate));
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Notification> getAllSeenByUser(int userId) {
        try{
            return notificationDao.getAllSeenByUser(userId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Notification> getAllSeenByUserDate(int userId, Date startDate, Date endDate) {
        try{
            return notificationDao.getAllSeenByUserDate(userId, startDate, serviceUtil.getDateWithMidNight(endDate));
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Notification> getAllNotSeenByUser(int userId) {
        try{
            return notificationDao.getAllNotSeenByUser(userId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Notification> getAllNotSeenByUserDate(int userId, Date startDate, Date endDate) {
        try{
            return notificationDao.getAllNotSeenByUserDate(userId, startDate, serviceUtil.getDateWithMidNight(endDate));
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public Notification getById(int notificationId) {
        try{
            return notificationDao.getById(notificationId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public void addPriceNotification(double price, int userId, int stockId, Date startDate, Date endDate) {
        User user = userDao.getById(userId);
        stockDailyDao.getListByDates(stockId,startDate, serviceUtil.getDateWithMidNight(endDate)).forEach( stock -> {
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

    public void delete(int notificationId) {
        notificationDao.delete(notificationId);
    }


}
