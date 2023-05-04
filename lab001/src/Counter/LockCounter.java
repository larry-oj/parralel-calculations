package Counter;

import java.util.concurrent.locks.ReentrantLock;

public class LockCounter implements ICounter {
    public int count = 0;
    ReentrantLock locker = new ReentrantLock();

    public void increment() {
        locker.lock();
        try {
            count++;
        } finally {
            locker.unlock();
        }
    }

    public void decrement() {
        locker.lock();
        try {
            count--;
        } finally {
            locker.unlock();
        }
    }

    public void print() {
        System.out.println("Lock. Result: " + count);
    }
}
