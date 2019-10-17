package com.eddgarvf.stockFinancial.util;

import com.eddgarvf.stockFinancial.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class ServiceUtil {

    private static final Logger logger = LoggerFactory.getLogger(ServiceUtil.class);

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public Date getDateWithMidNight(Date date) {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_FORMAT);
            String strDate = dateFormatter.format(date) + " 23:59:59";
            SimpleDateFormat dateTimeFormatter = new SimpleDateFormat(DATETIME_FORMAT);
            date = dateTimeFormatter.parse(strDate);
        }catch (ParseException e){
            logger.error(e.getMessage());
        }
        return date;
    }
}
