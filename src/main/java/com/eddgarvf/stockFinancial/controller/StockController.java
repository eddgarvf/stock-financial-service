package com.eddgarvf.stockFinancial.controller;

import com.eddgarvf.stockFinancial.model.Stock;
import com.eddgarvf.stockFinancial.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/stock")
public class StockController {

    private final StockService stockService;

    @Autowired
    public StockController(StockService stockService){
        this.stockService = stockService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/all")
    public List<Stock> getAllStocks(){
        try {
            return stockService.getAll();
        }catch (Exception e){
            return new ArrayList<>();
        }
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/{stockId}")
    public Stock getStockById(@PathVariable(name = "stockId") int stockId) {
        try {
            return stockService.getById(stockId);
        }catch (Exception e){
            return new Stock();
        }
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/add")
    public void addStock(@RequestBody Stock stock){
        stockService.add(stock);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(path = "/update")
    public void updateStock(@RequestBody Stock stock){
        stockService.update(stock);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(path = "/delete/{stockId}")
    public void deleteStock(@PathVariable(name = "stockId") int stockId){
        stockService.delete(stockId);
    }

}
