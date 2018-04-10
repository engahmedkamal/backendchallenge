package com.n26.backendchallenge.controller;

import com.n26.backendchallenge.model.Statistic;
import com.n26.backendchallenge.model.Transaction;
import com.n26.backendchallenge.service.exception.ExpiredTransactionException;
import com.n26.backendchallenge.service.interfaces.TransactionManager;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TransactionManagerController {

    @Autowired
    private TransactionManager transactionManager;

    @PostMapping(path = "/transactions")
    @ApiOperation(value = "Transactions", notes = "addTransaction")
    public ResponseEntity<Void> addTransaction(@RequestBody Transaction transaction) {
        try{
            transactionManager.addTransaction(transaction);
            return new ResponseEntity(HttpStatus.CREATED);
        }catch (ExpiredTransactionException e){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/statistics")
    @ApiOperation(value = "Calculate statistics", notes = "Calculate statistics")
    public Statistic calculateStatistic(){
        return transactionManager.calculateStatistic();
    }
}
