package com.degiro.portfolio.entity;

import com.degiro.portfolio.constants.TransactionStatusConstants;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACTION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;

    @Column(nullable = false)
    private int userId;

    @Column(columnDefinition = "varchar(255) default '"+TransactionStatusConstants.TXN_STATUS_SUBMITTED+"'",insertable = false)
    private String transactionStatus;

    @Column(nullable = false)
    private Double price;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",insertable = false)
    private LocalDateTime txnDateTime;
}
