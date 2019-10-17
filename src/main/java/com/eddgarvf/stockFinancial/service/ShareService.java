package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.dao.*;
import com.eddgarvf.stockFinancial.model.*;
import com.eddgarvf.stockFinancial.util.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ShareService {

    private static final Logger logger = LoggerFactory.getLogger(ShareService.class);

    private final StockDailyDao stockDailyDao;
    private final ShareDao shareDao;
    private final ShareQtyDao shareQtyDao;
    private final ServiceUtil serviceUtil;

    @Autowired
    public ShareService(StockDailyDao stockDailyDao,
                        ShareDao shareDao,
                        ShareQtyDao shareQtyDao,
                        ServiceUtil serviceUtil){
        this.stockDailyDao = stockDailyDao;
        this.shareDao = shareDao;
        this.shareQtyDao = shareQtyDao;
        this.serviceUtil = serviceUtil;
    }

    public List<Share> getAllByUser(int userId) {
        try{
            return shareDao.getAllByUser(userId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Share> getAllBoughtByUser(int userId) {
        try{
            return shareDao.getAllBoughtByUser(userId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Share> getAllBoughtByUserStock(int userId, int stockId) {
        try{
            return shareDao.getAllBoughtByUserStock(userId, stockId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Share> getAllBoughtByUserStockDates(int userId, int stockId, Date startDate, Date endDate) {
        try{
            return shareDao.getAllBoughtByUserStockDates(userId, stockId, startDate, serviceUtil.getDateWithMidNight(endDate));
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Share> getAllSoldByUser(int userId) {
        try{
            return shareDao.getAllSoldByUser(userId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Share> getAllSoldByUserStock(int userId, int stockId) {
        try{
            return shareDao.getAllSoldByUserStock(userId, stockId);
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Share> getAllSoldByUserStockDates(int userId, int stockId, Date startDate, Date endDate) {
        try{
            return shareDao.getAllSoldByUserStockDates(userId, stockId, startDate, serviceUtil.getDateWithMidNight(endDate));
        }catch (Exception e){
            logger.error(e.getMessage());
            return new ArrayList<>();
        }
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
        List<Share> soldShares = getAllSoldByUserStockDates(userId, stockId, startDate, serviceUtil.getDateWithMidNight(endDate));

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
}
