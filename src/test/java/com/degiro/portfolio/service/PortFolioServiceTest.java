package com.degiro.portfolio.service;

import com.degiro.portfolio.constants.CommonConstants;
import com.degiro.portfolio.dao.PortFolioDao;
import com.degiro.portfolio.responseobject.PortFolioObject;
import com.degiro.portfolio.util.BaseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PortFolioServiceTest
{
    @Mock
    PortFolioDao portFolioDao;

    @Mock
    BaseUtil baseUtil;

    @InjectMocks
    PortFolioService portFolioService;

    @BeforeEach
    public void init()
    {
    }

    @Test
    public void testGetIndividualPortFolioDetails()
    {
        PortFolioObject singlePortFolioObject=PortFolioObject
                .builder()
                .userId(1223)
                .amount(new Double("10"))
                .tillDate(LocalDateTime.parse("10-10-2022 10:00:00", DateTimeFormatter.ofPattern(CommonConstants.DATE_TIME_FORMAT)))
                .build();

        Mockito.when(portFolioDao.calculateUserAmount(1223,"10-10-2022 10:00:00"))
                .thenReturn(singlePortFolioObject);

        Mockito.when(baseUtil.formatDate(LocalDateTime.parse("10-10-2022 10:00:00", DateTimeFormatter.ofPattern(CommonConstants.DATE_TIME_FORMAT))))
                .thenReturn("10-10-2022 10:00:00");

        assertTrue(!portFolioService.getPortFolioDetails(1223,LocalDateTime.parse("10-10-2022 10:00:00", DateTimeFormatter.ofPattern(CommonConstants.DATE_TIME_FORMAT))).isEmpty());
    }


    @Test
    public void testGetAllPortFolioDetails()
    {
        PortFolioObject singlePortFolioObject=PortFolioObject
                .builder()
                .userId(1223)
                .amount(new Double("10"))
                .tillDate(LocalDateTime.parse("10-10-2022 10:00:00", DateTimeFormatter.ofPattern(CommonConstants.DATE_TIME_FORMAT)))
                .build();
        Mockito.when(portFolioDao.calculateAllUserAmount()).thenReturn(Arrays.asList(singlePortFolioObject));
        assertTrue(!portFolioService.getPortFolioDetails(null,null).isEmpty());
    }
}