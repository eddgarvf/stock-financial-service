package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.Stock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class StockDaoImpl implements StockDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Stock> getAll() {
        return entityManager.createQuery("FROM Stock ORDER BY name").getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Stock getById(int stockId) {
        return entityManager.find(Stock.class, stockId);
    }

    @Override
    public void add(Stock stock) {
        entityManager.persist(stock);
    }

    @Override
    public void update(Stock stock) {
        entityManager.merge(stock);
    }

    @Override
    public void delete(int stockId) {
        entityManager.remove(getById(stockId));
    }

}
