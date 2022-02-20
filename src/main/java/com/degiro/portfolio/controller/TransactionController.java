package com.degiro.portfolio.controller;

import com.degiro.portfolio.constants.CommonConstants;
import com.degiro.portfolio.entity.TransactionEntity;
import com.degiro.portfolio.responseobject.PortFolioObject;
import com.degiro.portfolio.service.PortFolioService;
import com.degiro.portfolio.service.TransactionService;
import com.sun.istack.NotNull;
import com.sun.net.httpserver.Authenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/*")
public class TransactionController
{
    /*
    * This class will do
    * 1. submit transaction
    * 2. cancel / update transaction
    * 3. getAll / specific user [userId,fromDate,toDate] transaction
    * 4. portfolio [sumAll transaction for [userId,asOfDate[toDate]] or [getAll]]
    * */

    @Autowired
    TransactionService transactionService;

    @Autowired
    PortFolioService portFolioService;

    @PostMapping("/submitTransaction")
    public ResponseEntity<String> submitTransaction(@RequestParam Integer userId,@RequestParam Double price)
    {
        if(transactionService.submitTransactionService(userId,price))
            return ResponseEntity.accepted().body("Success");
        else
            return ResponseEntity.badRequest().body("Failed");
    }

    @PatchMapping("/updateTransactionStatus")
    public ResponseEntity<String> updateStatusTransaction(@RequestParam Integer transactionId)
    {
        if(transactionService.updateStatusCancelledTransactionService(transactionId))
            return ResponseEntity.accepted().body("Success");
        else
            return ResponseEntity.badRequest().body("Failed");
    }

    @GetMapping("/getTransactionDetails")
    public ResponseEntity<List<TransactionEntity>> getTransactionDetails(@RequestParam(required = false) Integer userId,
                                                                         @RequestParam(required = false) @DateTimeFormat(pattern = CommonConstants.DATE_TIME_FORMAT) LocalDateTime fromDateTime,
                                                                         @RequestParam(required = false) @DateTimeFormat(pattern = CommonConstants.DATE_TIME_FORMAT)  LocalDateTime toDateTime)
    {
        return ResponseEntity.accepted().body(transactionService.getTransactionDetails(userId,fromDateTime,toDateTime));
    }

    @GetMapping("/getPortFolioDetails")
    public ResponseEntity<List<PortFolioObject>> getTransactionDetails(@RequestParam(required = false) Integer userId,
                                                                       @RequestParam(required = false) @DateTimeFormat(pattern = CommonConstants.DATE_TIME_FORMAT) LocalDateTime toDateTime)
    {
        return ResponseEntity.accepted().body(portFolioService.getPortFolioDetails(userId,toDateTime));
    }

}
