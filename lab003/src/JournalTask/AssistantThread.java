package JournalTask;

import java.util.Random;

public class AssistantThread extends Thread {
    private final Group group;
    private final Journal journal;

    public AssistantThread(Group group, Journal journal){
        this.group = group;
        this.journal = journal;
    }

    @Override
    public void run(){
        for (int i = 0; i < Journal.weeksToMark; i++) {
            for (Student student : group.Students) {
                Random rand = new Random();
                journal.putMark(rand.nextInt(101), student, group);
                System.out.println("Assistant graded student");
            }
        }

        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
