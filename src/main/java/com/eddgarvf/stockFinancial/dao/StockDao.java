package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.Stock;
import com.eddgarvf.stockFinancial.model.StockUser;

import java.util.List;

public interface StockDao {

    List<Stock> getAllStocks();
    List<StockUser> getStocksByUser(int userId);
    Stock getStockById(int stockId);
    void addStock(Stock stock);
    void buyStock(StockUser stockUser);
    void removeBoughtStock(StockUser stockUser);
    void updateStock(Stock stock);
    void deleteStock(int stockId);
}
