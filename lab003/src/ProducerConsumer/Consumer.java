package ProducerConsumer;

import java.util.Random;

public class Consumer implements Runnable {
    private final Drop drop;

    public Consumer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for (int i = 0; i < 100000; i++){
            drop.consume();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ignored) {}
        }
    }
}