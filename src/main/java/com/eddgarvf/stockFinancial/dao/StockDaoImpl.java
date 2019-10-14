package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.Stock;
import com.eddgarvf.stockFinancial.model.StockUser;
import com.eddgarvf.stockFinancial.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    @Transactional(readOnly = true)
    public List<StockUser> getStocksByUser(int userId) {
        Query query = entityManager.createNamedQuery("getStocksByUser");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public StockUser getStockByUserAndStock(StockUser stockUser) {
        return getStockUser(stockUser.getUser().getId(), stockUser.getStock().getId());
    }

    @Override
    @Transactional(readOnly = true)
    public Stock getStockById(int stockId) {
        return entityManager.find(Stock.class, stockId);
    }

    @Override
    public void addStock(Stock stock) {
        entityManager.persist(stock);
    }

    @Override
    public void buyStock(StockUser stockUser) {
        entityManager.persist(getStockUserWitnQtyIncremented(stockUser.getUser().getId(), stockUser.getStock().getId()));
    }

    @Override
    public void removeBoughtStock(StockUser stockUser) {
        StockUser stockUserFromDb = getStockUser(stockUser.getUser().getId(), stockUser.getStock().getId());
        if (null != stockUserFromDb) {
            entityManager.remove(stockUserFromDb);
        }
    }

    @Override
    public void updateStock(Stock stock) {
        entityManager.merge(stock);
    }

    @Override
    public void deleteStock(int stockId) {
        entityManager.remove(getStockById(stockId));
    }

    private StockUser getStockUser(int userId, int stockId){
        try{
            Query query = entityManager.createNamedQuery("getStockByUserAndId");
            query.setParameter("userId", userId);
            query.setParameter("stockId", stockId);
            return (StockUser) query.getSingleResult();
        }catch(NoResultException e){
            return null;
        }
    }

    private StockUser getStockUserWitnQtyIncremented(int userId, int stockId){
        try{
            Query query = entityManager.createNamedQuery("getStockByUserAndId");
            query.setParameter("userId", userId);
            query.setParameter("stockId", stockId);
            StockUser stockUser = (StockUser) query.getSingleResult();
            stockUser.setStockQty(stockUser.getStockQty() + 1);
            return stockUser;
        }catch(NoResultException e){
            return getNewStockUser(userId, stockId);
        }
    }

    private StockUser getNewStockUser(int userId, int stockId){
        StockUser stockUser = new StockUser();
        User user = new User();
        user.setId(userId);
        Stock stock = new Stock();
        stock.setId(stockId);
        stockUser.setStockQty(1);
        stockUser.setUser(user);
        stockUser.setStock(stock);
        return stockUser;
    }
}
