package Counter;

public class SyncBlockCounter implements ICounter {
    private int counter = 0;

    public void increment() {
        synchronized (this) {
            counter++;
        }
    }

    public void decrement() {
        synchronized (this) {
            counter--;
        }
    }

    public void print() {
        System.out.println("Sync block. Result: " + counter);
    }
}
