package com.eddgarvf.stockFinancial.controller.model;

import java.util.List;

public class SellSharesRequest {

    private int userId;
    private int stockId;
    private List<Integer> shareIds;

    public SellSharesRequest(int userId, int stockId, List<Integer> shareIds) {
        this.userId = userId;
        this.stockId = stockId;
        this.shareIds = shareIds;
    }

    public int getUserId() {
        return userId;
    }

    public int getStockId() {
        return stockId;
    }

    public List<Integer> getShareIds() {
        return shareIds;
    }
}
