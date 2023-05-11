package Bank;

import java.util.Arrays;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    public static final int NTEST = 10000;
    private final int[] accounts;
    private long transactionsNum;
    private final Lock locker = new ReentrantLock();
    public Bank(int n, int initialBalance) {
        accounts = new int[n];
        Arrays.fill(accounts, initialBalance);
        transactionsNum = 0;
    }

    public void transfer(int from, int to, int amount) {
        accounts[from] -= amount;
        accounts[to] += amount;
        transactionsNum++;
        if (transactionsNum % NTEST == 0)
            printTransactionsData();
    }
    public synchronized void transferSync(int from, int to, int amount) {
        accounts[from] -= amount;
        accounts[to] += amount;
        transactionsNum++;
        if (transactionsNum % NTEST == 0)
            printTransactionsData();
    }
    public void transferSyncBlock(int from, int to, int amount) {
        synchronized (this){
            accounts[from] -= amount;
            accounts[to] += amount;
            transactionsNum++;
            if (transactionsNum % NTEST == 0)
                printTransactionsData();
        }

    }
    public void transferLocker(int from, int to, int amount) {
        locker.lock();
        try{
            accounts[from] -= amount;
            accounts[to] += amount;
            transactionsNum++;
            if (transactionsNum % NTEST == 0)
                printTransactionsData();
        }finally{
            locker.unlock();
        }
    }
    public void printTransactionsData() {
        int sum = 0;
        for (int account : accounts)
            sum += account;
        System.out.println("Transactions:" + transactionsNum + " Sum: " + sum);
    }
    public int size() {
        return accounts.length;
    }
}