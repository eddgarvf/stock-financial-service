package com.eddgarvf.stockFinancial.controller;

import com.eddgarvf.stockFinancial.controller.model.SellSharesRequest;
import com.eddgarvf.stockFinancial.model.Share;
import com.eddgarvf.stockFinancial.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/share")
public class ShareController {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private final ShareService shareService;

    @Autowired
    public ShareController(ShareService shareService){
        this.shareService = shareService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping( path = "/get/all/{userId}")
    public List<Share> getAllByUser(@PathVariable(name = "userId") int userId) {
        return shareService.getAllByUser(userId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping( path = "/get/bought/{userId}")
    public List<Share> getAllBoughtByUser(@PathVariable(name = "userId") int userId) {
        return shareService.getAllBoughtByUser(userId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping( path = "/get/bought/{userId}/{stockId}")
    public List<Share> getAllBoughtByUserStock(
            @PathVariable(name = "userId") int userId,
            @PathVariable(name = "stockId") int stockId) {
        return shareService.getAllBoughtByUserStock(userId, stockId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping( path = "/get/bought/{userId}/{stockId}/{startDate}/{endDate}")
    public List<Share> getAllBoughtByUserStockDates(
            @PathVariable(name = "userId") int userId,
            @PathVariable(name = "stockId") int stockId,
            @PathVariable(name = "startDate") @DateTimeFormat(pattern = DATE_FORMAT) Date startDate,
            @PathVariable(name = "endDate")   @DateTimeFormat(pattern = DATE_FORMAT) Date endDate) {
        return shareService.getAllBoughtByUserStockDates(userId, stockId, startDate, endDate);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping( path = "/get/sold/{userId}")
    public List<Share> getAllSoldByUser(@PathVariable(name = "userId") int userId) {
        return shareService.getAllSoldByUser(userId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping( path = "/get/sold/{userId}/{stockId}")
    public List<Share> getAllSoldByUserStock(
            @PathVariable(name = "userId") int userId,
            @PathVariable(name = "stockId") int stockId) {
        return shareService.getAllSoldByUserStock(userId, stockId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping( path = "/get/sold/{userId}/{stockId}/{startDate}/{endDate}")
    public List<Share> getAllSoldByUserStockDates(
            @PathVariable(name = "userId") int userId,
            @PathVariable(name = "stockId") int stockId,
            @PathVariable(name = "startDate") @DateTimeFormat(pattern = DATE_FORMAT) Date startDate,
            @PathVariable(name = "endDate")   @DateTimeFormat(pattern = DATE_FORMAT) Date endDate) {
        return shareService.getAllSoldByUserStockDates(userId, stockId, startDate, endDate);
    }
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/buy/{userId}/{stockId}/{shares}")
    public void buyShares(
            @PathVariable(name = "userId") int userId,
            @PathVariable(name = "stockId") int stockId,
            @PathVariable(name = "shares") int shares){
        shareService.buyShare(userId, stockId, shares);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/sell")
    public void shellShares(@RequestBody SellSharesRequest request ){
        shareService.sellShare(request.getUserId(), request.getStockId(), request.getShareIds());
    }
}
