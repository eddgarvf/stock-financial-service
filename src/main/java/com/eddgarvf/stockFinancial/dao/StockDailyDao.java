package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.StockDaily;

import java.util.Date;
import java.util.List;

public interface StockDailyDao {

    StockDaily get(int stockDailyId);
    StockDaily getLastStockDailyRecord(int stockId);
    List<StockDaily> getListByDates(int stockId, Date startDate, Date endDate);
    void add(StockDaily stockDaily);
    void update(StockDaily stockDaily);
}
