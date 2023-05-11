package Symbols;

public class Sync {
    private boolean turn;
    private int symbols;
    private int lines;
    private boolean stop;

    public Sync() {
        turn = true;
        symbols = 0;
        lines = 0;
        stop = false;
    }

    public synchronized boolean getTurn() {
        return turn;
    }

    public synchronized boolean isStop() {
        return stop;
    }

    public synchronized void waitAndChange(boolean control, char s) {
        while (getTurn() != control) {
            try {
                wait();
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        if(stop){
            notifyAll();
            return;
        }
        System.out.print(s);
        turn = !turn;
        symbols++;
        if (symbols == 100) {
            symbols = 0;
            System.out.println();
            lines++;
        }
        if (lines == 100) {
            stop = true;
        }
        notifyAll();

    }
}
