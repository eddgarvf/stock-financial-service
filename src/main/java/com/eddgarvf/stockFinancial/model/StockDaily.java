package com.eddgarvf.stockFinancial.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "stock_daily")
public class StockDaily {

    @Id
    @GeneratedValue
    int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_stock")
    private Stock stock;
    private Date date;
    private double priceOpen;
    private double priceClose;
    private double priceChange;
    private int volume;

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
