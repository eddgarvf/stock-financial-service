package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.dao.StockDao;
import com.eddgarvf.stockFinancial.model.Stock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    private static final Logger logger = LoggerFactory.getLogger(StockService.class);

    private final StockDao stockDao;

    @Autowired
    public StockService(StockDao stockDao){
        this.stockDao = stockDao;
    }

    public List<Stock> getAll() {
        try{
            return stockDao.getAll();
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public Stock getById(int stockId) {
        try{
            return stockDao.getById(stockId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public void add(Stock stock) {
        stockDao.add(stock);
    }

    public void update(Stock stock) {
        stockDao.update(stock);
    }

    public void delete(int stockId) {
        stockDao.delete(stockId);
    }
}
