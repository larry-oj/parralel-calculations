package Counter;

public class Counters {
    public static void main(String[] args) {
        ICounter[] counters = {
            new AsyncCounter(),
            new SyncCounter(),
            new SyncBlockCounter(),
            new LockCounter()
        };
        int threadNum = 2;
        int opsCount = 100000;
        for(ICounter counter : counters) {
            try {
                runCounter(counter, threadNum, opsCount);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public static void runCounter(ICounter counter, int threadNum, int countOfCalcs) {
        long start = System.currentTimeMillis();
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            if (i % 2 == 0) {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < countOfCalcs; j++) {
                        counter.increment();
                    }
                });
            } else {
                threads[i] = new Thread(() -> {
                    for (int j = 0; j < countOfCalcs; j++) {
                        counter.decrement();
                    }
                });
            }
        }
        for (int i = 0; i < threadNum; i++) {
            threads[i].start();
        }
        for (int i = 0; i < threadNum; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        counter.print();
        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("Time elapsed: " + timeElapsed + " ms");
    }
}
