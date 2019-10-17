package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.controller.model.StockDailyRecordRequest;
import com.eddgarvf.stockFinancial.dao.StockDailyDao;
import com.eddgarvf.stockFinancial.dao.StockDao;
import com.eddgarvf.stockFinancial.model.StockDaily;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StockDailyService {

    private static final Logger logger = LoggerFactory.getLogger(StockDailyService.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

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
        return stockDailyDao.getListByDates(stockId, startDate, getDateWithMidNight(endDate));
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

    private Date getDateWithMidNight(Date date) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
            String strDate = dateFormatter.format(date) + " 23:59:59";
            SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATETIME_FORMAT);
            date = dateTimeFormatter.parse(strDate);
        }catch (ParseException e){
            logger.error(e.getMessage());
        }
        return date;
    }
}
