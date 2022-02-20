package com.degiro.portfolio.service;

import com.degiro.portfolio.constants.TransactionStatusConstants;
import com.degiro.portfolio.dao.TransactionDao;
import com.degiro.portfolio.entity.TransactionEntity;
import com.degiro.portfolio.util.BaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService
{
    @Autowired
    TransactionDao transactionDao;

    @Autowired
    BaseUtil baseUtil;

    @Transactional
    public boolean submitTransactionService(Integer userId,Double price)
    {
        return transactionDao.save(TransactionEntity.builder().userId(userId).price(price).build()).getTransactionId()!=0;
    }

    @Transactional
    public boolean updateStatusCancelledTransactionService(Integer transactionId)
    {
        return transactionDao.updateTransactionStatus(transactionId,TransactionStatusConstants.TXN_STATUS_CANCELLED)>0;
    }

    @Transactional(readOnly = true)
    public List<TransactionEntity> getTransactionDetails(Integer userId, LocalDateTime fromDateTime, LocalDateTime toDateTime)
    {
        if(userId!=null && baseUtil.validateDate(fromDateTime,toDateTime))
            return transactionDao.findByUserId(userId,baseUtil.formatDate(fromDateTime),baseUtil.formatDate(toDateTime));
        else
            return transactionDao.findAll();
    }
}
