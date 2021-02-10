package com.gmail.velikiydan.task_1;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicBank implements IBank {
    public static final int NTEST = 10000;
    // added atomic int arr
    private final AtomicIntegerArray accounts;
    // added atomic ntransacts
    private final AtomicLong ntransacts = new AtomicLong(0);


    public AtomicBank(int n, int initialBalance) {
        accounts = new AtomicIntegerArray(n);
        for (int i = 0; i < n; i++) {
            accounts.set(i, initialBalance);
        }
    }

    public void transfer(int from, int to, int amount) {
        accounts.getAndAdd(from, -amount);
        accounts.getAndAdd(to, amount);

        ntransacts.incrementAndGet();

        long transactionNum = ntransacts.get();
        if (transactionNum % NTEST == 0) {
            test(transactionNum);
        }

    }

    public void test(long transactionNum) {
        int sum = 0;
        for (int i = 0; i < accounts.length(); i++) {
            sum += accounts.get(i);
        }
        System.out.println("Transactions:" + transactionNum
                + " Sum: " + sum);
    }

    public int size() {
        return accounts.length();
    }
}