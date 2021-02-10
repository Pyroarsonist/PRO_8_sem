package com.gmail.velikiydan.task_1;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class BankWithLock implements IBank {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts = 0;

    // added lock
    private final ReentrantLock lock = new ReentrantLock();

    public BankWithLock(int n, int initialBalance) {
        accounts = new int[n];
        Arrays.fill(accounts, initialBalance);
        ntransacts = 0;
    }

    public void transfer(int from, int to, int amount) {
        try {
            lock.lock();

            accounts[from] -= amount;
            accounts[to] += amount;
            ntransacts++;
            if (ntransacts % NTEST == 0)
                test();
        } finally {
            lock.unlock();
        }

    }

    public void test() {
        int sum = 0;
        for (int account : accounts) sum += account;
        System.out.println("Transactions:" + ntransacts
                + " Sum: " + sum);
    }

    public int size() {
        return accounts.length;
    }
}