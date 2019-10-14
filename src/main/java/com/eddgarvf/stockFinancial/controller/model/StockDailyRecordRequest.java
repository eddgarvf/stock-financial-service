package com.eddgarvf.stockFinancial.controller.model;

import java.util.Date;

public class StockDailyRecordRequest {

    private int stockId;
    private Date date;
    private double priceOpen;
    private double priceClose;
    private double priceChange;
    private int volume;

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPriceOpen() {
        return priceOpen;
    }

    public void setPriceOpen(double priceOpen) {
        this.priceOpen = priceOpen;
    }

    public double getPriceClose() {
        return priceClose;
    }

    public void setPriceClose(double priceClose) {
        this.priceClose = priceClose;
    }

    public double getPriceChange() {
        return priceChange;
    }

    public void setPriceChange(double priceChange) {
        this.priceChange = priceChange;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
