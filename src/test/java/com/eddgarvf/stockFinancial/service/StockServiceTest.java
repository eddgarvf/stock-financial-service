package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.dao.StockDao;
import com.eddgarvf.stockFinancial.model.Stock;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class StockServiceTest {

    @Mock
    StockDao stockDao;

    StockService stockService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        stockService = new StockService(stockDao);
    }

    @Test
    public void testGetAllStocks(){
        List<Stock> stockList = new ArrayList<>();
        Stock stock1 = new Stock();
        stock1.setId(1);
        stock1.setCode("code1");
        stock1.setName("stock1");
        Stock stock2 = new Stock();
        stock2.setId(3);
        stock2.setCode("code2");
        stock2.setName("stock2");
        stockList.add(stock1);
        stockList.add(stock2);
        when(stockDao.getAll()).thenReturn(stockList);
        stockService.getAll();

        verify(stockDao, times(1)).getAll();
    }


}
