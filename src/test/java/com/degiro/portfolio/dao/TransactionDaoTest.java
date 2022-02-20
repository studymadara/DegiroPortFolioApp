package com.degiro.portfolio.dao;

import com.degiro.portfolio.constants.TransactionStatusConstants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TransactionDaoTest
{
    @Autowired
    TransactionDao transactionDao;

    @Test
    void findByUserId()
    {
        assertTrue(transactionDao.findByUserId(1234,"10-12-2022 10:00:00","10-12-2022 10:00:00").isEmpty());
    }

    @Test
    void updateTransactionStatus()
    {
        assertTrue(transactionDao.updateTransactionStatus(1234, TransactionStatusConstants.TXN_STATUS_CANCELLED)==0);
    }
}