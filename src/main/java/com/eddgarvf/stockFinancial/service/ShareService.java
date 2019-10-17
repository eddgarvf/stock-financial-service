package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.dao.*;
import com.eddgarvf.stockFinancial.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ShareService {

    private static final Logger logger = LoggerFactory.getLogger(ShareService.class);

    private final StockDailyDao stockDailyDao;
    private final ShareDao shareDao;
    private final ShareQtyDao shareQtyDao;

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    public ShareService(StockDailyDao stockDailyDao,
                        ShareDao shareDao,
                        ShareQtyDao shareQtyDao){
        this.stockDailyDao = stockDailyDao;
        this.shareDao = shareDao;
        this.shareQtyDao = shareQtyDao;
    }

    public List<Share> getAllByUser(int userId) {
        return shareDao.getAllByUser(userId);
    }

    public List<Share> getAllBoughtByUser(int userId) {
        return shareDao.getAllBoughtByUser(userId);
    }

    public List<Share> getAllBoughtByUserStock(int userId, int stockId) {
        return shareDao.getAllBoughtByUserStock(userId, stockId);
    }

    public List<Share> getAllBoughtByUserStockDates(int userId, int stockId, Date startDate, Date endDate) {
        return shareDao.getAllBoughtByUserStockDates(userId, stockId, startDate, getDateWithMidNight(endDate));
    }

    public List<Share> getAllSoldByUser(int userId) {
        return shareDao.getAllSoldByUser(userId);
    }

    public List<Share> getAllSoldByUserStock(int userId, int stockId) {
        return shareDao.getAllSoldByUserStock(userId, stockId);
    }

    public List<Share> getAllSoldByUserStockDates(int userId, int stockId, Date startDate, Date endDate) {
        return shareDao.getAllSoldByUserStockDates(userId, stockId, startDate, getDateWithMidNight(endDate));
    }

    public void add(Share share) {
        shareDao.add(share);
    }

    public void update(Share share) {
        shareDao.update(share);
    }

    public void buyShare(int userId, int stockId, int shares){

        ShareQty actualShareQty = shareQtyDao.getShares(userId, stockId);
        StockDaily actualStock = stockDailyDao.getLastStockDailyRecord(stockId);

        saveSharesBought(shares, actualStock.getPriceChange(), actualShareQty.getStock(), actualShareQty.getUser());

        ShareQty updatedShareQty = new ShareQty();
        updatedShareQty.setUser(actualShareQty.getUser());
        updatedShareQty.setStock(actualShareQty.getStock());

        if(actualShareQty.getId() > 0) {
            updatedShareQty.setId(actualShareQty.getId());
        }
        updatedShareQty.setShares(actualShareQty.getShares() + shares);
        shareQtyDao.update(updatedShareQty);
    }

    public void sellShare(int userId, int stockId, List<Integer> shareIds){

        ShareQty actualShareQty = shareQtyDao.getShares(userId, stockId);
        StockDaily actualStock = stockDailyDao.getLastStockDailyRecord(stockId);

        if( null != actualShareQty && actualShareQty.getShares() > 0){

            shareIds.forEach(shareId -> {
                Share currentShare = shareDao.getById(shareId);

                currentShare.setSellPrice(actualStock.getPriceChange());
                currentShare.setBuySell(false);
                currentShare.setSellDatetime(new Date());
                shareDao.update(currentShare);

                ShareQty updatedShareQty = new ShareQty();
                updatedShareQty.setUser(currentShare.getUser());
                updatedShareQty.setStock(currentShare.getStock());
                updatedShareQty.setId(actualShareQty.getId());
                updatedShareQty.setShares(actualShareQty.getShares() - 1);
                shareQtyDao.update(updatedShareQty);
            });
        }
    }

    public double getGainLossPerDate(int userId, int stockId, Date startDate, Date endDate){
        double totalPurchasePrice = 0;
        double totalSellPrice = 0;
        List<Share> soldShares = getAllSoldByUserStockDates(userId, stockId, startDate, getDateWithMidNight(endDate));

        for(Share share : soldShares){
            totalPurchasePrice += share.getBuyPrice();
            totalSellPrice += share.getSellPrice();
        }
        return totalSellPrice - totalPurchasePrice;
    }

    private void saveSharesBought(int shares, double price, Stock stock, User user) {

        for(int i = 0; i < shares; i++){
            Share share = new Share();
            share.setBuySell(true);
            share.setBuyPrice(price);
            share.setStock(stock);
            share.setUser(user);
            shareDao.add(share);
        }
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
