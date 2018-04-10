package com.n26.backendchallenge.service.interfaces;

import com.n26.backendchallenge.model.Statistic;
import com.n26.backendchallenge.model.Transaction;
import com.n26.backendchallenge.service.exception.ExpiredTransactionException;

public interface TransactionManager {
    void addTransaction(Transaction transaction) throws ExpiredTransactionException;

    Statistic calculateStatistic();
}
