package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.ShareQty;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Transactional
@Repository
public class ShareQtyDaoImpl implements ShareQtyDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public ShareQty getById(int userId) {
        return entityManager.find(ShareQty.class, userId);
    }

    @Override
    @Transactional(readOnly = true)
    public ShareQty getShares(int userId, int stockId) {
        try{
            Query query = entityManager.createNamedQuery("getShareQty");
            query.setParameter("userId", userId);
            query.setParameter("stockId", stockId);
            return (ShareQty) query.getSingleResult();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void add(ShareQty shareQty) {
        entityManager.persist(shareQty);
    }

    @Override
    public void update(ShareQty shareQty) {
        entityManager.merge(shareQty);
    }
}
