package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.Stock;
import com.eddgarvf.stockFinancial.model.StockUser;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class StockDaoImpl implements StockDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Stock> getAllStocks() {
        return entityManager.createQuery("FROM Stock ORDER BY id").getResultList();
    }

    @Override
    public List<StockUser> getStocksByUser(int userId) {
        Query query = entityManager.createNamedQuery("getStocksByUser");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public Stock getStockById(int stockId) {
        return entityManager.find(Stock.class, stockId);
    }

    @Override
    public void addStock(Stock stock) {
        entityManager.persist(stock);
    }

    @Override
    public void buyStock(StockUser stockUser) {
        entityManager.persist(stockUser);
    }

    @Override
    public void removeBoughtStock(StockUser stockUser) {
        Query query = entityManager.createNamedQuery("getStockByUserAndId");
        query.setParameter("userId", stockUser.getUser().getId());
        query.setParameter("stockId", stockUser.getStock().getId());
        query.getResultList().forEach(entity -> entityManager.remove(entity));
    }

    @Override
    public void updateStock(Stock stock) {
        entityManager.merge(stock);
    }

    @Override
    public void deleteStock(int stockId) {
        entityManager.remove(getStockById(stockId));
    }
}
