package com.n26.backendchallenge.model;

public class Transaction {

    private double amount;
    private long timestamp;
    private int count = 1;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Transaction mergeTracnsactions(Transaction transaction){
        this.amount += transaction.getAmount();
        this.count += transaction.getCount();
        return this;
    }
}
