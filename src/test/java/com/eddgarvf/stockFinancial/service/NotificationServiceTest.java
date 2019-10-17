package com.eddgarvf.stockFinancial.service;

import com.eddgarvf.stockFinancial.dao.*;
import com.eddgarvf.stockFinancial.model.*;
import com.eddgarvf.stockFinancial.util.ServiceUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class NotificationServiceTest {

    @Mock
    StockDailyDao stockDailyDao;
    @Mock
    UserDao userDao;
    @Mock
    NotificationDao notificationDao;
    @Mock
    ServiceUtil serviceUtil;

    NotificationService notificationService;

    private static final int USER_ID = 1;
    private static final int STOCK_ID = 1;
    private static final Date DATE = new Date();

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        notificationService = new NotificationService(notificationDao, stockDailyDao, userDao, serviceUtil);

        Stock stock1 = new Stock();
        stock1.setId(1);
        stock1.setCode("code1");
        stock1.setName("stock1");

        User user1 = new User();
        user1.setId(1);
        user1.setName("Ed");
        user1.setEmail("e@h.com");

        StockDaily stockDaily = new StockDaily();
        stockDaily.setStock(stock1);
        stockDaily.setVolume(1000);
        stockDaily.setPriceOpen(1);
        stockDaily.setPriceClose(12);
        stockDaily.setPriceChange(12);

        List<StockDaily> stockDailyList = new ArrayList<>();
        stockDailyList.add(stockDaily);

        when(userDao.getById(USER_ID)).thenReturn(user1);
        when(serviceUtil.getDateWithMidNight(DATE)).thenReturn(DATE);
        when(stockDailyDao.getListByDates(STOCK_ID, DATE, DATE)).thenReturn(stockDailyList);

    }

    @Test
    public void testPriceNotification(){
        notificationService.addPriceNotification(12, 1, 1, DATE, DATE);
        verify(notificationDao, times(1)).add(any());
    }
}
