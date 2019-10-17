package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.Notification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class NotificationDaoImpl implements NotificationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Notification> getAllByUser(int userId) {
        Query query = entityManager.createNamedQuery("getAllNotificationsByUser");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Notification> getAllByUserDate(int userId, Date startDate, Date endDate) {
        Query query = entityManager.createNamedQuery("getAllNotificationsByUserDate");
        query.setParameter("userId", userId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Notification> getAllSeenByUser(int userId) {
        Query query = entityManager.createNamedQuery("getAllNotificationsByUserSeen");
        query.setParameter("userId", userId);
        query.setParameter("seen", true);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Notification> getAllSeenByUserDate(int userId, Date startDate, Date endDate) {
        Query query = entityManager.createNamedQuery("getAllNotificationsByUserSeenDates");
        query.setParameter("userId", userId);
        query.setParameter("seen", true);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Notification> getAllNotSeenByUser(int userId) {
        Query query = entityManager.createNamedQuery("getAllNotificationsByUserSeen");
        query.setParameter("userId", userId);
        query.setParameter("seen", false);
        return query.getResultList();
    }

    @Override
    public List<Notification> getAllNotSeenByUserDate(int userId, Date startDate, Date endDate) {
        Query query = entityManager.createNamedQuery("getAllNotificationsByUserSeenDates");
        query.setParameter("userId", userId);
        query.setParameter("seen", false);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Notification getById(int notificationId) {
        return entityManager.find(Notification.class, notificationId);    }

    @Override
    public void add(Notification notification) {
        entityManager.persist(notification);
    }

    @Override
    public void update(Notification notification) {
        entityManager.merge(notification);
    }

    @Override
    public void delete(int notification) {
        entityManager.remove(notification);
    }
}
