package com.n26.backendchallenge.service;

import com.n26.backendchallenge.model.Statistic;
import com.n26.backendchallenge.model.Transaction;
import com.n26.backendchallenge.service.exception.ExpiredTransactionException;
import com.n26.backendchallenge.service.interfaces.TransactionManager;
import org.springframework.stereotype.Service;

@Service
public class TransactionManagerImpl implements TransactionManager {

    private static Transaction[] transactions = new Transaction[60000];

    @Override
    public void addTransaction(Transaction transaction) throws ExpiredTransactionException {
        Long currentTimeMillis = System.currentTimeMillis();
        if (isExpiredTransaction(transaction, currentTimeMillis)) {
            throw new ExpiredTransactionException();
        }
        synchronized (transactions) {
            for (int i = 0; i < transactions.length; i++) {
                if (transactions[i] != null && isExpiredTransaction(transactions[i], currentTimeMillis)) {
                    transactions[i] = null;
                }
            }
            int index = (int) (currentTimeMillis - transaction.getTimestamp());
            if (transactions[index] == null) {
                transactions[index] = transaction;
            } else {
                transactions[index] = transactions[index].mergeTracnsactions(transaction);
            }
        }
    }

    @Override
    public Statistic calculateStatistic() {
        Long currentTimeMillis = System.currentTimeMillis();
        Statistic statistic = new Statistic();
        synchronized (transactions) {
            for (int i = 0; i < transactions.length; i++) {
                if (transactions[i] != null && isExpiredTransaction(transactions[i], currentTimeMillis)) {
                    transactions[i] = null;
                } else if (transactions[i] != null) {
                    statistic.addTransaction(transactions[i]);
                }
            }
        }
        return statistic;
    }

    private Boolean isExpiredTransaction(Transaction transaction, Long currentTimeMillis) {
        return (currentTimeMillis - transaction.getTimestamp()) > (60 * 1000);
    }
}
