package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.dao.ShareDao;
import com.eddgarvf.stockFinancial.dao.ShareQtyDao;
import com.eddgarvf.stockFinancial.dao.StockDailyDao;
import com.eddgarvf.stockFinancial.model.*;
import com.eddgarvf.stockFinancial.util.ServiceUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ShareServiceTest {

    @Mock
    StockDailyDao stockDailyDao;
    @Mock
    ShareDao shareDao;
    @Mock
    ShareQtyDao shareQtyDao;
    @Mock
    ServiceUtil serviceUtil;

    ShareService shareService;

    private static final int USER_ID = 1;
    private static final int STOCK_ID = 1;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        shareService = new ShareService(stockDailyDao, shareDao, shareQtyDao, serviceUtil);

        Stock stock1 = new Stock();
        stock1.setId(1);
        stock1.setCode("code1");
        stock1.setName("stock1");

        User user1 = new User();
        user1.setId(1);
        user1.setName("Ed");
        user1.setEmail("e@h.com");

        ShareQty actualShareQty = new ShareQty();
        actualShareQty.setId(1);
        actualShareQty.setShares(1);
        actualShareQty.setStock(stock1);
        actualShareQty.setUser(user1);

        StockDaily actualStock = new StockDaily();
        actualShareQty.setUser(user1);
        actualShareQty.setStock(stock1);
        actualShareQty.setShares(1);
        actualShareQty.setId(1);

        when(shareQtyDao.getShares(USER_ID, STOCK_ID)).thenReturn(actualShareQty);
        when(stockDailyDao.getLastStockDailyRecord(STOCK_ID)).thenReturn(actualStock);

    }

    @Test
    public void testBuyShare(){
        shareService.buyShare(STOCK_ID, USER_ID, 1);
        verify(shareQtyDao, times(1)).update(any());
    }

    @Test
    public void testSellShare(){
        List<Integer> shareIds = new ArrayList<>();
        shareIds.add(1);

        Stock stock1 = new Stock();
        stock1.setId(1);
        stock1.setCode("code1");
        stock1.setName("stock1");

        User user1 = new User();
        user1.setId(1);
        user1.setName("Ed");
        user1.setEmail("e@h.com");

        Share share1 = new Share();
        share1.setId(1);
        share1.setStock(stock1);
        share1.setUser(user1);
        share1.setBuySell(true);
        share1.setBuyPrice(100);
        share1.setSellPrice(200);

        when(shareDao.getById(1)).thenReturn(share1);
        shareService.sellShare(STOCK_ID, USER_ID, shareIds);
        verify(shareQtyDao, times(1)).update(any());
    }
}
