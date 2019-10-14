package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.dao.StockDao;
import com.eddgarvf.stockFinancial.dao.UserDao;
import com.eddgarvf.stockFinancial.model.Stock;
import com.eddgarvf.stockFinancial.model.StockUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    private final StockDao stockDao;
    private final UserDao userDao;

    @Autowired
    public StockService(StockDao stockDao,
                        UserDao userDao){
        this.stockDao = stockDao;
        this.userDao = userDao;
    }

    public List<Stock> getAllStocks() {
        return stockDao.getAllStocks();
    }

    public Stock getStockById(int stockId) {
        return stockDao.getStockById(stockId);
    }

    public List<StockUser> getStocksByUserId(int userId) {
        return stockDao.getStocksByUser(userId);
    }

    public StockUser getStockByUserAndStock(int userId, int stockId){
        StockUser stockUser = new StockUser();
        stockUser.setUser(userDao.getUserById(userId));
        stockUser.setStock(stockDao.getStockById(stockId));
        return stockDao.getStockByUserAndStock(stockUser);
    }

    public void addStock(Stock stock) {
        stockDao.addStock(stock);
    }

    public void buyStock(int userId, int stockId) {
        StockUser stockUser = new StockUser();
        stockUser.setUser(userDao.getUserById(userId));
        stockUser.setStock(stockDao.getStockById(stockId));
        stockDao.buyStock(stockUser);
    }

    public void removeBoughtStock(int userId, int stockId) {
        StockUser stockUser = new StockUser();
        stockUser.setUser(userDao.getUserById(userId));
        stockUser.setStock(stockDao.getStockById(stockId));
        stockDao.removeBoughtStock(stockUser);
    }

    public void updateStock(Stock stock) {
        stockDao.updateStock(stock);
    }

    public void deleteStock(int stockId) {
        stockDao.deleteStock(stockId);
    }
}
