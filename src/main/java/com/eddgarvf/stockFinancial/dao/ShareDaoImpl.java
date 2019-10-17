package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.Share;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class ShareDaoImpl implements ShareDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Share getById(int shareId) {
        return entityManager.find(Share.class, shareId);
    }

    @Override
    public List<Share> getAllByUser(int userId) {
        Query query = entityManager.createNamedQuery("getAllSharesByUser");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Share> getAllBoughtByUser(int userId) {
        Query query = entityManager.createNamedQuery("getAllSharesByUserAction");
        query.setParameter("userId", userId);
        query.setParameter("buySell",true);
        return query.getResultList();
    }

    @Override
    public List<Share> getAllBoughtByUserStock(int userId, int stockId) {
        Query query = entityManager.createNamedQuery("getAllSharesByUserActionStock");
        query.setParameter("userId", userId);
        query.setParameter("stockId", stockId);
        query.setParameter("buySell", true);
        return query.getResultList();
    }

    @Override
    public List<Share> getAllBoughtByUserStockDates(int userId, int stockId, Date startDate, Date endDate) {
        Query query = entityManager.createNamedQuery("getAllBoughtSharesByUserStockDates");
        query.setParameter("userId", userId);
        query.setParameter("stockId", stockId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @Override
    public List<Share> getAllSoldByUser(int userId) {
        Query query = entityManager.createNamedQuery("getAllSharesByUserAction");
        query.setParameter("userId", userId);
        query.setParameter("buySell", false);
        return query.getResultList();
    }

    @Override
    public List<Share> getAllSoldByUserStock(int userId, int stockId) {
        Query query = entityManager.createNamedQuery("getAllSharesByUserActionStock");
        query.setParameter("userId", userId);
        query.setParameter("stockId", stockId);
        query.setParameter("buySell", false);
        return query.getResultList();
    }

    @Override
    public List<Share> getAllSoldByUserStockDates(int userId, int stockId, Date startDate, Date endDate) {
        Query query = entityManager.createNamedQuery("getAllSoldSharesByUserStockDates");
        query.setParameter("userId", userId);
        query.setParameter("stockId", stockId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @Override
    public void add(Share share) {
        entityManager.persist(share);
    }

    @Override
    public void update(Share share) {
        entityManager.merge(share);
    }
}
