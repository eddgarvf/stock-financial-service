package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.Stock;

import java.util.List;

public interface StockDao {

    List<Stock> getAll();
    Stock getById(int stockId);
    void add(Stock stock);
    void update(Stock stock);
    void delete(int stockId);
}
