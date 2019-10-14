package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.controller.model.StockDailyRecordRequest;
import com.eddgarvf.stockFinancial.dao.StockDailyDao;
import com.eddgarvf.stockFinancial.dao.StockDao;
import com.eddgarvf.stockFinancial.model.StockDaily;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StockDailyService {

    private final StockDailyDao stockDailyDao;
    private final StockDao stockDao;

    @Autowired
    public StockDailyService(StockDailyDao stockDailyDao,
                             StockDao stockDao){
        this.stockDailyDao = stockDailyDao;
        this.stockDao = stockDao;
    }


    public StockDaily getStockDailyRecord(int stockDailyId){
        return stockDailyDao.getStockDailyRecord(stockDailyId);
    }

    public List<StockDaily> getStocksDailyRecordsByDate(int stockId, Date startDate, Date endDate) {
        return stockDailyDao.getStocksDailyRecordsByDate(stockId, startDate, endDate);
    }


    public void addStockDailyRecord(StockDailyRecordRequest request) {
        StockDaily stockDaily = new StockDaily();
        stockDaily.setDate(request.getDate());
        stockDaily.setPriceChange(request.getPriceChange());
        stockDaily.setPriceClose(request.getPriceClose());
        stockDaily.setPriceOpen(request.getPriceOpen());
        stockDaily.setVolume(request.getVolume());
        stockDaily.setStock(stockDao.getStockById(request.getStockId()));
        stockDailyDao.addStockDailyRecord(stockDaily);
    }


    public void updateStockDailyRecord(StockDaily stockDaily) {
        stockDailyDao.updateStockDailyRecord(stockDaily);
    }

}
