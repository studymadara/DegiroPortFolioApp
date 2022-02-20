package com.degiro.portfolio.dao;

import com.degiro.portfolio.constants.CommonConstants;
import com.degiro.portfolio.constants.TransactionStatusConstants;
import com.degiro.portfolio.entity.TransactionEntity;
import com.degiro.portfolio.responseobject.PortFolioObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PortFolioDao extends JpaRepository<TransactionEntity,Long>
{
    @Query("select new com.degiro.portfolio.responseobject.PortFolioObject(t.userId,t.txnDateTime as tillDate, sum(t.price) as amount) from TransactionEntity t " +
            "where t.userId = ?1 and t.txnDateTime <= parsedatetime(?2,'"+ CommonConstants.DATE_TIME_FORMAT +"') and t.transactionStatus ='"+ TransactionStatusConstants.TXN_STATUS_SUBMITTED+"'"+
            " group by t.txnDateTime order by t.txnDateTime desc")
    PortFolioObject calculateUserAmount(Integer userId, String toDateTime);

    @Query(value = "select new com.degiro.portfolio.responseobject.PortFolioObject((t.userId),max(t.txnDateTime) as tillDate, sum(t.price) as amount) from TransactionEntity t " +
            "where t.transactionStatus = '"+ TransactionStatusConstants.TXN_STATUS_SUBMITTED +"'" +
            " group by t.userId order by t.userId",nativeQuery = false)
    List<PortFolioObject> calculateAllUserAmount();
}
