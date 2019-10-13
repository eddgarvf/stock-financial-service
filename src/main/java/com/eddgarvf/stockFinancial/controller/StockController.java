package com.eddgarvf.stockFinancial.controller;

import com.eddgarvf.stockFinancial.model.Stock;
import com.eddgarvf.stockFinancial.model.StockUser;
import com.eddgarvf.stockFinancial.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return stockService.getAllStocks();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/all/{userId}")
    public List<Stock> getStocksByUserId(@PathVariable(name = "userId") int userId){
        return stockService.getStocksByUserId(userId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/{stockId}")
    public Stock getStockById(@PathVariable(name = "stockId") int stockId){
        return stockService.getStockById(stockId);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/add")
    public void addStock(@RequestBody Stock stock){
        stockService.addStock(stock);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/buy/{userId}/{stockId}")
    public void buyStock(@PathVariable(name = "userId") int userId, @PathVariable(name = "stockId") int stockId){
        stockService.buyStock(userId, stockId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(path = "/update")
    public void updateStock(@RequestBody Stock stock){
        stockService.updateStock(stock);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(path = "/delete/{stockId}")
    public void deleteStock(@PathVariable(name = "stockId") int stockId){
        stockService.deleteStock(stockId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(path = "/remove/{userId}/{stockId}")
    public void removeBoughtStock(@PathVariable(name = "userId") int userId, @PathVariable(name = "stockId") int stockId){
        stockService.removeBoughtStock(userId, stockId);
    }
}
