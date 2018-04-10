package com.n26.backendchallenge.model;

public class Statistic {
    private double sum;
    private double avg;
    private double max;
    private double min;
    private long count;

    public void addTransaction(Transaction transaction) {
        avg = (transaction.getAmount() + sum) / (count + transaction.getCount());
        sum += transaction.getAmount();
        max = transaction.getAmount() > max ? transaction.getAmount() : max;
        min = transaction.getAmount() < min ? transaction.getAmount() : min;
        count += transaction.getCount();
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public long getCount() {
        return count;
    }
}
