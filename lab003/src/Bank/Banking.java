package Bank;

public class Banking {
    public static final int ACCOUNTS_NUM = 10;
    public static final int INITIAL_BALANCE = 10000;

    public static void main(String[] args) {
        Bank bank = new Bank(ACCOUNTS_NUM, INITIAL_BALANCE);
        for (int i = 0; i < ACCOUNTS_NUM; i++){
            TransferThread t = new TransferThread(bank, i, INITIAL_BALANCE);
            t.start () ;
        }
    }
}

