package com.degiro.portfolio.util;

import com.degiro.portfolio.constants.CommonConstants;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class BaseUtil
{
    public boolean validateDate(LocalDateTime fromDateTime, LocalDateTime toDateTime)
    {
        return fromDateTime!=null && toDateTime!=null && (fromDateTime.isBefore(toDateTime) || fromDateTime.isEqual(toDateTime));
    }

    public String formatDate(LocalDateTime localDateTime)
    {
        return localDateTime.format(DateTimeFormatter.ofPattern(CommonConstants.DATE_TIME_FORMAT));
    }
}
