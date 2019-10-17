package com.eddgarvf.stockFinancial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "share")
@NamedQuery(query = "SELECT s FROM Share s WHERE s.user.id = :userId", name = "getAllSharesByUser")
@NamedQuery(query = "SELECT s FROM Share s WHERE s.user.id = :userId AND s.buySell = :buySell", name = "getAllSharesByUserAction")
@NamedQuery(query = "SELECT s FROM Share s WHERE s.user.id = :userId AND s.stock.id = :stockId AND s.buySell = :buySell", name = "getAllSharesByUserActionStock")
@NamedQuery(query = "SELECT s FROM Share s WHERE s.user.id = :userId AND s.stock.id = :stockId AND s.buySell = true  AND s.buyDatetime BETWEEN :startDate AND :endDate", name = "getAllBoughtSharesByUserStockDates")
@NamedQuery(query = "SELECT s FROM Share s WHERE s.user.id = :userId AND s.stock.id = :stockId AND s.buySell = false AND s.sellDatetime BETWEEN :startDate AND :endDate", name = "getAllSoldSharesByUserStockDates")
public class Share {

    @Id
    @GeneratedValue
    int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_stock")
    private Stock stock;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;
    @Column(name = "buy_price")
    private double buyPrice;
    @JsonIgnore
    @Column(name = "sell_price")
    private Double sellPrice;
    @Column(name = "buy_sell")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean buySell;
    @Column(name = "buy_datetime")
    private Date buyDatetime;
    @JsonIgnore
    @Column(name = "sell_datetime")
    private Date sellDatetime;

    @PrePersist
    protected void onCreate() {
        this.buyDatetime = new Date();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public boolean isBuySell() {
        return buySell;
    }

    public void setBuySell(boolean buySell) {
        this.buySell = buySell;
    }

    public Date getBuyDatetime() {
        return buyDatetime;
    }

    public void setBuyDatetime(Date buyDatetime) {
        this.buyDatetime = buyDatetime;
    }

    public Date getSellDatetime() {
        return sellDatetime;
    }

    public void setSellDatetime(Date sellDatetime) {
        this.sellDatetime = sellDatetime;
    }
}
