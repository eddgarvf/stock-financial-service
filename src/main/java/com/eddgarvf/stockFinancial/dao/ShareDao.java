package com.eddgarvf.stockFinancial.dao;

import com.eddgarvf.stockFinancial.model.Share;

import java.util.Date;
import java.util.List;

public interface ShareDao {

    Share getById(int shareId);
    List<Share> getAllBoughtByUser(int userId);
    List<Share> getAllBoughtByUserStock(int userId, int stockId);
    List<Share> getAllBoughtByUserStockDates(int userId, int stockId, Date startDate, Date endDate);
    List<Share> getAllSoldByUser(int userId);
    List<Share> getAllSoldByUserStock(int userId, int stockId);
    List<Share> getAllSoldByUserStockDates(int userId, int stockId, Date startDate, Date endDate);
    List<Share> getAllByUser(int userId);
    void add(Share share);
    void update(Share share);
}
