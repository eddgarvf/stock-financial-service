package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.StockDaily;

import java.util.Date;
import java.util.List;

public interface StockDailyDao {

    StockDaily getStockDailyRecord(int stockDailyId);
    List<StockDaily> getStocksDailyRecordsByDate(int stockId, Date startDate, Date endDate);
    void addStockDailyRecord(StockDaily stockDaily);
    void updateStockDailyRecord(StockDaily stockDaily);
}
