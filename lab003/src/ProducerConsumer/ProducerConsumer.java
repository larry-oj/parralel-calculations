package ProducerConsumer;

public class ProducerConsumer {
    public static void main(String[] args) {
        Drop drop = new Drop(1000);
        Thread prod = new Thread(new Producer(drop));
        Thread cons = new Thread(new Consumer(drop));
        prod.start();
        cons.start();
        try{
            prod.join();
            cons.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
