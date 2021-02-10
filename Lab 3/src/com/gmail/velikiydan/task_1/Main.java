package com.gmail.velikiydan.task_1;

public class Main {
    public static final int NACCOUNTS = 10;
    public static final int INITIAL_BALANCE = 10000;

    public static void main(String[] args) {

//        IBank b = new BankWithLock(NACCOUNTS, INITIAL_BALANCE);
//        IBank b = new AtomicBank(NACCOUNTS, INITIAL_BALANCE);
        IBank b = new SyncBank(NACCOUNTS, INITIAL_BALANCE);

        for (int i = 0; i < NACCOUNTS; i++) {
            TransferThread t = new TransferThread(b, i,
                    INITIAL_BALANCE);
            t.setPriority(Thread.NORM_PRIORITY + i % 2);
            t.start();
        }
    }
}
