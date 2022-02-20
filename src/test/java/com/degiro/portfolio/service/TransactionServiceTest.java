package com.degiro.portfolio.service;

import com.degiro.portfolio.constants.TransactionStatusConstants;
import com.degiro.portfolio.dao.TransactionDao;
import com.degiro.portfolio.entity.TransactionEntity;
import com.degiro.portfolio.util.BaseUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    TransactionDao transactionDao;

    @Mock
    BaseUtil baseUtil;

    @InjectMocks
    TransactionService transactionService;

    TransactionEntity transactionEntity;

    @BeforeEach
    public void init()
    {
        transactionEntity=TransactionEntity.builder()
                .userId(1224)
                .price(new Double("100"))
                .transactionStatus(TransactionStatusConstants.TXN_STATUS_SUBMITTED)
                .transactionId(100)
                .build();
    }

    @Test
    void submitTransactionService()
    {
        Mockito.when(transactionDao.save(Mockito.any(TransactionEntity.class))).thenReturn(transactionEntity);
        assertTrue(transactionService.submitTransactionService(transactionEntity.getUserId(), transactionEntity.getPrice()));
    }

    @Test
    void updateStatusCancelledTransactionService()
    {
        Mockito.when(transactionDao.updateTransactionStatus(transactionEntity.getTransactionId(),TransactionStatusConstants.TXN_STATUS_CANCELLED))
                .thenReturn(1);
        assertTrue(transactionService.updateStatusCancelledTransactionService(transactionEntity.getTransactionId()));
    }

    @Test
    void getAllTransactionDetails()
    {
        Mockito.when(transactionDao.findAll()).thenReturn(Arrays.asList());
        assertTrue(transactionService.getTransactionDetails(null,null,null).isEmpty());
    }
}