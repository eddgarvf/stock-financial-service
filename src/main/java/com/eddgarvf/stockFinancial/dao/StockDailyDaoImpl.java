package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.StockDaily;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class StockDailyDaoImpl implements StockDailyDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public StockDaily getStockDailyRecord(int stockDailyId){
        return entityManager.find(StockDaily.class, stockDailyId);
    }

    @Override
    public List<StockDaily> getStocksDailyRecordsByDate(int stockId, Date startDate, Date endDate) {
        Query query = entityManager.createNamedQuery("getStockDailyRecorrsByDate");
        query.setParameter("stockId", stockId);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @Override
    public void addStockDailyRecord(StockDaily stockDaily) {
        entityManager.persist(stockDaily);
    }

    @Override
    public void updateStockDailyRecord(StockDaily stockDaily) {
        entityManager.merge(stockDaily);
    }

}
