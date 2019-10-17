package com.eddgarvf.stockFinancial.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

@Entity
@Table(name = "stock_daily")
@NamedQuery(query = "SELECT sd FROM StockDaily sd WHERE sd.stock.id = :stockId AND sd.datetime BETWEEN :startDate AND :endDate ORDER BY sd.datetime DESC", name = "getStockDailyRecordsByDate")
@NamedQuery(query = "SELECT sd FROM StockDaily sd WHERE sd.stock.id = :stockId ORDER BY sd.datetime DESC", name = "getLastStockDailyRecord")
public class StockDaily {

    @Id
    @GeneratedValue
    int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_stock")
    private Stock stock;
    private Date datetime;
    private double priceOpen;
    private double priceClose;
    private double priceChange;
    private int volume;

    @PrePersist
    protected void onCreate() {
        this.datetime = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
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
