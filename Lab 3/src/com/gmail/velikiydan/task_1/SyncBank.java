package com.gmail.velikiydan.task_1;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

public class SyncBank implements IBank {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long ntransacts = 0;


    public SyncBank(int n, int initialBalance) {
        accounts = new int[n];
        Arrays.fill(accounts, initialBalance);
        ntransacts = 0;
    }

    public synchronized void transfer(int from, int to, int amount) {

        accounts[from] -= amount;
        accounts[to] += amount;
        ntransacts++;
        if (ntransacts % NTEST == 0)
            test();

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