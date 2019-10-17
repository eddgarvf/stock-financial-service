package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.controller.model.StockDailyRecordRequest;
import com.eddgarvf.stockFinancial.dao.StockDailyDao;
import com.eddgarvf.stockFinancial.dao.StockDao;
import com.eddgarvf.stockFinancial.model.StockDaily;
import com.eddgarvf.stockFinancial.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StockDailyService {

    private static final Logger logger = LoggerFactory.getLogger(StockDailyService.class);

    private final StockDailyDao stockDailyDao;
    private final StockDao stockDao;
    private final ServiceUtil serviceUtil;

    @Autowired
    public StockDailyService(StockDailyDao stockDailyDao,
                             StockDao stockDao,
                             ServiceUtil serviceUtil){
        this.stockDailyDao = stockDailyDao;
        this.stockDao = stockDao;
        this.serviceUtil = serviceUtil;
    }

    public StockDaily get(int stockDailyId){
        try{
            return stockDailyDao.get(stockDailyId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public StockDaily getLastStockDailyRecord(int stockId){
        try{
            return stockDailyDao.getLastStockDailyRecord(stockId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }
    }

    public List<StockDaily> getListByDates(int stockId, Date startDate, Date endDate) {
        try{
            return stockDailyDao.getListByDates(stockId, startDate, serviceUtil.getDateWithMidNight(endDate));
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
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
