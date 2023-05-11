package JournalTask;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Journal {
    public static int marks = 0;
    public static int weeksToMark = 3;
    private final Lock locker = new ReentrantLock();
    Map<Group, Map<Student, List<Integer>>> groupMap = new HashMap<>();
    public Journal(List<Group> groups){
        for (Group group : groups){
            Map<Student, List<Integer>> marks = new HashMap<>();
            for (Student student: group.Students) {
                marks.put(student, Arrays.asList());
            }
            groupMap.put(group,marks);
        }
    }
    public void putMark(Integer mark, Student student, Group group){
        locker.lock();
        try {
            var groupMarks = groupMap.get(group);
            marks++;
            List<Integer> prevList = groupMarks.get(student);
            List<Integer> copy = new ArrayList<>(prevList);
            copy.add(mark);
            groupMarks.put(student,copy);
        } finally {
            locker.unlock();
        }
    }

    public void printMarks() {
        groupMap.forEach((group, studentMap) -> {
            System.out.println("Group: " + group);

            studentMap.forEach((student, integerList) -> {
                System.out.println("Student: " + student);

                integerList.forEach(integerValue -> {
                    System.out.println("Integer Value: " + integerValue);
                });
            });
        });
    }
}
