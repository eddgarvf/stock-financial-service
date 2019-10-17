package com.eddgarvf.stockFinancial.controller.model;

public class StockQtyResponse {

    private int id;
    private String name;
    private String code;
    private int shares;
    private double amount;

    public StockQtyResponse(){}

    public StockQtyResponse(int id, String name, String code, int shares, double amount) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.shares = shares;
        this.amount = amount;
    }

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

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
