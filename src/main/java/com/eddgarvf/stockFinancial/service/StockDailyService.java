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

    public StockDaily get(int stockDailyId){
        return stockDailyDao.get(stockDailyId);
    }

    public StockDaily getLastStockDailyRecord(int stockId){
        return stockDailyDao.getLastStockDailyRecord(stockId);
    }

    public List<StockDaily> getListByDates(int stockId, Date startDate, Date endDate) {
        return stockDailyDao.getListByDates(stockId, startDate, endDate);
    }


    public void add(StockDailyRecordRequest request) {
        StockDaily stockDaily = new StockDaily();
        stockDaily.setPriceChange(request.getPriceChange());
        stockDaily.setPriceClose(request.getPriceClose());
        stockDaily.setPriceOpen(request.getPriceOpen());
        stockDaily.setVolume(request.getVolume());
        stockDaily.setStock(stockDao.getById(request.getStockId()));
        stockDailyDao.add(stockDaily);
    }


    public void update(StockDaily stockDaily) {
        stockDailyDao.update(stockDaily);
    }

}
