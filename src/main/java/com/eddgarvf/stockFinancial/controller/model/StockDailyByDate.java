package com.eddgarvf.stockFinancial.controller.model;

import java.util.Date;

public class StockDailyByDate {

    int id;
    private Date date;
    private double priceOpen;
    private double priceClose;
    private double priceChange;
    private int volume;

    public StockDailyByDate(int id, Date date, double priceOpen, double priceClose, double priceChange, int volume) {
        this.id = id;
        this.date = date;
        this.priceOpen = priceOpen;
        this.priceClose = priceClose;
        this.priceChange = priceChange;
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
