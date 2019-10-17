package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.controller.model.StockDailyRecordRequest;
import com.eddgarvf.stockFinancial.dao.StockDailyDao;
import com.eddgarvf.stockFinancial.dao.StockDao;
import com.eddgarvf.stockFinancial.model.Stock;
import com.eddgarvf.stockFinancial.util.ServiceUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class StockDailyServiceTest {

    @Mock
    StockDailyDao stockDailyDao;
    @Mock
    StockDao stockDao;
    @Mock
    ServiceUtil serviceUtil;

    StockDailyService stockDailyService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        stockDailyService = new StockDailyService(stockDailyDao, stockDao, serviceUtil);
    }

    @Test
    public void testAddStockDailyRecord(){
        StockDailyRecordRequest request = new StockDailyRecordRequest();
        request.setPriceChange(5);
        request.setPriceClose(5);
        request.setPriceOpen(1);
        request.setStockId(1);
        request.setVolume(1000);
        when(stockDao.getById(1)).thenReturn(new Stock());
        stockDailyService.add(request);
        verify(stockDailyDao, times(1)).add(any());
    }
}
