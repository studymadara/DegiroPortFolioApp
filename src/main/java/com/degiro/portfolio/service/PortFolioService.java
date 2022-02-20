package com.degiro.portfolio.service;

import com.degiro.portfolio.dao.PortFolioDao;
import com.degiro.portfolio.responseobject.PortFolioObject;
import com.degiro.portfolio.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class PortFolioService
{
    @Autowired
    PortFolioDao portFolioDao;

    @Autowired
    BaseUtil baseUtil;

    @Transactional
    public List<PortFolioObject> getPortFolioDetails(Integer userId, LocalDateTime toDateTime)
    {
        if(userId!=null && toDateTime!=null)
            return Arrays.asList(portFolioDao.calculateUserAmount(userId,baseUtil.formatDate(toDateTime)));
        else
            return portFolioDao.calculateAllUserAmount();
    }
}
