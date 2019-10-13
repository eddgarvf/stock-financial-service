package com.eddgarvf.stockFinancial.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String code;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
    private Set<StockDaily> stockDailyRecords = new HashSet<>();
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
    private Set<StockUser> stockUserRecords = new HashSet<>();

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<StockDaily> getStockDailyRecords() {
        return stockDailyRecords;
    }

    public void setStockDailyRecords(Set<StockDaily> stockDailyRecords) {
        this.stockDailyRecords = stockDailyRecords;
    }

    public Set<StockUser> getStockUserRecords() {
        return stockUserRecords;
    }

    public void setStockUserRecords(Set<StockUser> stockUserRecords) {
        this.stockUserRecords = stockUserRecords;
    }
}
