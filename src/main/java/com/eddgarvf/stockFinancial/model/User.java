package com.eddgarvf.stockFinancial.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Set<StockUser> getStockUserRecords() {
//        return stockUserRecords;
//    }
//
//    public void setStockUserRecords(Set<StockUser> stockUserRecords) {
//        this.stockUserRecords = stockUserRecords;
//    }
}
