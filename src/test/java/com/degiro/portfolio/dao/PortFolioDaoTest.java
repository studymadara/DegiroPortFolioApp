package com.degiro.portfolio.dao;

import com.degiro.portfolio.responseobject.PortFolioObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PortFolioDaoTest {

    @Autowired
    PortFolioDao portFolioDao;

    @Test
    void calculateUserAmount()
    {
        assertTrue(portFolioDao.calculateUserAmount(1234,"12-02-2022 10:00:00")==null);
    }

    @Test
    void calculateAllUserAmount()
    {
        assertTrue(portFolioDao.calculateAllUserAmount().isEmpty());
    }
}