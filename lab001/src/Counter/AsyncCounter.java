package Counter;

public class AsyncCounter implements ICounter {
    private int counter = 0;

    public void increment(){
        counter++;
    }
    public void decrement(){
        counter--;
    }
    public void print(){
        System.out.println("Async. Result: " + counter);
    }
}
