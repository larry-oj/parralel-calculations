package ProducerConsumer;

import java.util.Random;

public class Producer implements Runnable {
    private Drop drop;

    public Producer(Drop drop) {
        this.drop = drop;
    }

    public void run() {
        Random random = new Random();
        for (int i = 0; i < 100000; i++){
            drop.produce(random.nextInt(500));
        }

    }
}