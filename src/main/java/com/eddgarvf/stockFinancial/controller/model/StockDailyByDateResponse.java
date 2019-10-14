package com.eddgarvf.stockFinancial.controller.model;

import com.eddgarvf.stockFinancial.model.Stock;

import java.util.List;

public class StockDailyByDateResponse {

    Stock stock;
    List<StockDailyByDate> stocksDaily;

    public StockDailyByDateResponse(){}

    public StockDailyByDateResponse(Stock stock, List<StockDailyByDate> stocksDaily) {
        this.stock = stock;
        this.stocksDaily = stocksDaily;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public List<StockDailyByDate> getStocksDaily() {
        return stocksDaily;
    }

    public void setStocksDaily(List<StockDailyByDate> stocksDaily) {
        this.stocksDaily = stocksDaily;
    }
}
