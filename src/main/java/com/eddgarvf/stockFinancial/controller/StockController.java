package com.eddgarvf.stockFinancial.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stocks")
public class StockController {

    @GetMapping(value = "/get/{userId}")
    public String getStocksByUserId(@PathVariable("userId") long userId){
        return "UserId: " + userId;
    }

}
