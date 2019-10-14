package com.eddgarvf.stockFinancial.controller;

import com.eddgarvf.stockFinancial.controller.model.StockDailyByDate;
import com.eddgarvf.stockFinancial.controller.model.StockDailyByDateResponse;
import com.eddgarvf.stockFinancial.controller.model.StockDailyRecordRequest;
import com.eddgarvf.stockFinancial.model.StockDaily;
import com.eddgarvf.stockFinancial.service.StockDailyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/stock-daily")
public class StockDailyController {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    private final StockDailyService stockDailyService;

    @Autowired
    public StockDailyController(StockDailyService stockDailyService){
        this.stockDailyService = stockDailyService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/{stockId}/{startDate}/{endDate}")
    public StockDailyByDateResponse getStocksDailyRecordsByDate(@PathVariable(name = "stockId") int stockId,
                                                                      @PathVariable(name = "startDate") @DateTimeFormat(pattern=DATE_FORMAT) String startDate,
                                                                      @PathVariable(name = "endDate")  @DateTimeFormat(pattern=DATE_FORMAT) String endDate){
        try {
            List<StockDaily> stockDailyList = stockDailyService.getStocksDailyRecordsByDate(
                    stockId,
                    new SimpleDateFormat(DATE_FORMAT).parse(startDate),
                    new SimpleDateFormat(DATE_FORMAT).parse(endDate));
            return new StockDailyByDateResponse(
                    stockDailyList.get(0).getStock(),
                    stockDailyList.stream().map(stockDaily ->
                            new StockDailyByDate(
                                stockDaily.getId(),
                                stockDaily.getDate(),
                                stockDaily.getPriceOpen(),
                                stockDaily.getPriceClose(),
                                stockDaily.getPriceChange(),
                                stockDaily.getVolume()
                        )
                    ).collect(Collectors.toList())
            );
        }catch (Exception e){
            return new StockDailyByDateResponse();
        }
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping(path = "/get/{stockDailyId}")
    public StockDaily getStockDailyRecord(@PathVariable(name = "stockDailyId") int stockDailyId){
        try {
            return stockDailyService.getStockDailyRecord(stockDailyId);
        }catch (Exception e){
            return new StockDaily();
        }
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(path = "/add")
    public void addStock(@RequestBody StockDailyRecordRequest request){
        stockDailyService.addStockDailyRecord(request);
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PutMapping(path = "/update")
    public void updateStockDailyRecord(@RequestBody StockDaily stockDaily){
        stockDailyService.updateStockDailyRecord(stockDaily);
    }

}
