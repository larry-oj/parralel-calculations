package Billiards;



public class BallThread extends Thread {
    private final Ball b;
    private final BallThread parentThread;
    private final boolean isForJoining;

    public BallThread(Ball ball) {
        b = ball;
        parentThread = null;
        isForJoining = false;
    }
    public BallThread(Ball ball, BallThread parent) {
        b = ball;
        parentThread = parent;
        isForJoining = true;
    }

    @Override
    public void run() {
        try {
            if(isForJoining && parentThread != null)
                parentThread.join();
            while (true) {
                b.move();
                System.out.println("Thread name = "
                        + Thread.currentThread().getName());
                Thread.sleep(2);
            }
        } catch (InterruptedException ex) {
            if(!ex.getMessage().equals("sleep interrupted"))
                System.out.println(ex);
        }
    }


}