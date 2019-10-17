package com.eddgarvf.stockFinancial.controller;

import com.eddgarvf.stockFinancial.model.Notification;
import com.eddgarvf.stockFinancial.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/notification")
public class NotificationController {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/{userId}")
    public List<Notification> getAllByUser(@PathVariable(name = "userId") int userId) {
        return notificationService.getAllByUser(userId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/{userId}/{startDate}/{endDate}")
    public List<Notification> getAllByUserDate(
            @PathVariable(name = "userId") int userId,
            @PathVariable(name = "startDate") @DateTimeFormat(pattern = DATE_FORMAT) Date startDate,
            @PathVariable(name = "endDate")   @DateTimeFormat(pattern = DATE_FORMAT) Date endDate) {
        return notificationService.getAllByUserDate(userId, startDate, endDate);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/seen/{userId}")
    public List<Notification> getAllSeenByUser(@PathVariable(name = "userId") int userId) {
        return notificationService.getAllSeenByUser(userId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/seen/{userId}/{startDate}/{endDate}")
    public List<Notification> getAllSeenByUserDate(
            @PathVariable(name = "userId") int userId,
            @PathVariable(name = "startDate") @DateTimeFormat(pattern = DATE_FORMAT) Date startDate,
            @PathVariable(name = "endDate")   @DateTimeFormat(pattern = DATE_FORMAT) Date endDate) {
        return notificationService.getAllSeenByUserDate(userId, startDate, endDate);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/not-seen/{userId}")
    public List<Notification> getAllNotSeenByUser(@PathVariable(name = "userId") int userId) {
        return notificationService.getAllNotSeenByUser(userId);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/not-seen/{userId}/{startDate}/{endDate}")
    public List<Notification> getAllNotSeenByUserDate(
            @PathVariable(name = "userId") int userId,
            @PathVariable(name = "startDate") @DateTimeFormat(pattern = DATE_FORMAT) Date startDate,
            @PathVariable(name = "endDate")   @DateTimeFormat(pattern = DATE_FORMAT) Date endDate) {
        return notificationService.getAllNotSeenByUserDate(userId, startDate, endDate);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get-notification/{notificationId}")
    public Notification getById(@PathVariable(name = "notificationId") int notificationId) {
        return notificationService.getById(notificationId);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/add/stock-price/{price}/{userId}/{stockId}/{startDate}/{endDate}")
    public void addPriceNotification(
            @PathVariable(name = "price") double price,
            @PathVariable(name = "userId") int userId,
            @PathVariable(name = "stockId") int stockId,
            @PathVariable(name = "startDate") @DateTimeFormat(pattern = DATE_FORMAT) Date startDate,
            @PathVariable(name = "endDate")   @DateTimeFormat(pattern = DATE_FORMAT) Date endDate) {
        notificationService.addPriceNotification(price, userId, stockId, startDate, endDate);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/add")
    public void add(@RequestBody Notification notification) {
        notificationService.add(notification);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(path = "/update")
    public void update(@RequestBody Notification notification) {
        notificationService.update(notification);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @DeleteMapping(path = "/delete/{notificationId}")
    public void delete(@PathVariable(name = "notificationId") int notificationId) {
        notificationService.delete(notificationId);
    }

}
