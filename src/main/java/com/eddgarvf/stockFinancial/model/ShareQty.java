package com.eddgarvf.stockFinancial.model;

import javax.persistence.*;

@Entity
@Table(name = "share_qty")
@NamedQuery(query = "SELECT sq FROM ShareQty sq WHERE sq.user.id = :userId AND sq.stock.id = :stockId", name = "getShareQty")
public class ShareQty {

    @Id
    @GeneratedValue
    int id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_stock")
    private Stock stock;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;
    int shares;

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

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }
}
