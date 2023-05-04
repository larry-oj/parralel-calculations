package Counter;

public class SyncCounter implements ICounter {
    private int counter = 0;

    public synchronized void increment() {
        counter++;
    }

    public synchronized void decrement() {
        counter--;
    }

    public void print() {
        System.out.println("Sync method. Result: " + counter);
    }
}
