package JournalTask;

import java.util.List;
import java.util.Random;

public class TeacherThread extends Thread {
    private final Journal journal;
    private final List<Group> groups;

    public TeacherThread(List<Group> groups,Journal journal){
        this.journal = journal;
        this.groups = groups;
    }

    @Override
    public void run(){
        for (int i = 0; i < Journal.weeksToMark; i++) {
            for (Group group : groups) {
                for (Student student : group.Students) {
                    Random rand = new Random();
                    journal.putMark(rand.nextInt(101), student, group);
                    System.out.println("Teacher graded student");
                }
                System.out.println("Teacher graded groups");
            }
        }
        try {
            Thread.sleep(0);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
