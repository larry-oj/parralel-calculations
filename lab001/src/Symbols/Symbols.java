package Symbols;

public class Symbols {
    public static void main(String[] args) {
        Thread first;
        Thread second;
        if(true) {
            Sync permission = new Sync();
            first = new Thread(new SymbolSync('-', permission, true));
            second = new Thread(new SymbolSync('|', permission, false));
        }
        else{
            first = new Thread(new SymbolAsync('-'));
            second = new Thread(new SymbolAsync('|'));
        }
        first.start();
        second.start();
    }
}
