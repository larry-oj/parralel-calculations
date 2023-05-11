package ProducerConsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Drop {
    final Lock lock = new ReentrantLock();
    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();
    final int[] items;
    int currentCount;
    int totalItemsCount;
    public Drop(int itemsCount){
        items = new int[itemsCount];
        totalItemsCount = itemsCount;
        currentCount = 0;
    }
    public int consume()  {
        lock.lock();
        try{
            while(currentCount == 0){
                notEmpty.await();
            }
            int item = items[currentCount-1];
            currentCount--;
            System.out.println("Consume element: " + item);
            notFull.signalAll();
            return item;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    public void produce(int item) {
        lock.lock();
        try{
            while(currentCount == totalItemsCount){
                notFull.await();
            }
            items[currentCount] = item;
            currentCount++;
            System.out.println("Produce element: " + item);
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}