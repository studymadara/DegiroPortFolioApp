package com.degiro.portfolio.dao;

import com.degiro.portfolio.constants.CommonConstants;
import com.degiro.portfolio.constants.TransactionStatusConstants;
import com.degiro.portfolio.entity.TransactionEntity;
import com.degiro.portfolio.responseobject.PortFolioObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionDao extends JpaRepository<TransactionEntity,Long>
{
    @Query("select t from TransactionEntity t where t.userId = ?1 and t.txnDateTime between parsedatetime(?2,'"+ CommonConstants.DATE_TIME_FORMAT +"') and parsedatetime(?3,'"+ CommonConstants.DATE_TIME_FORMAT +"')")
    List<TransactionEntity> findByUserId(Integer userId, String fromDate,String toDateTime);

    @Modifying
    @Query("update TransactionEntity t set t.transactionStatus = ?2 where t.transactionId = ?1 ")
    int updateTransactionStatus(int transactionId,String transactionStatus);
}
