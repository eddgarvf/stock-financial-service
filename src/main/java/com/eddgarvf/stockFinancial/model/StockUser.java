package com.eddgarvf.stockFinancial.model;

import javax.persistence.*;

@Entity
@Table(name = "stock_user")
@NamedQuery(query = "SELECT su FROM StockUser su WHERE su.user.id = :userId", name = "getStocksByUser")
@NamedQuery(query = "SELECT su FROM StockUser su WHERE su.user.id = :userId AND su.stock.id = :stockId", name = "getStockByUserAndId")
public class StockUser {

    @Id
    @GeneratedValue
    int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_stock")
    private Stock stock;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;
    @Column(name = "stock_qty")
    int stockQty;

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

    public int getStockQty() {
        return stockQty;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }
}
