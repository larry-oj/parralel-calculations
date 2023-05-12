package task1;

import common.Folder;

import java.io.File;
import java.io.IOException;

public class TaskOne {
    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        var filesPath = new File("src/files").getAbsolutePath();
        var file = new File(filesPath);
        var folder = Folder.fromDirectory(file);

        var wordCounter = new WordCounter();
        var map = wordCounter.countOccurrencesInParallel(folder);

        var wordSum = 0;
        System.out.println("Words:");
        for (var item : map.entrySet()) {
            System.out.printf("%s: %s%n", item.getKey(), item.getValue());
            wordSum += item.getValue();
        }

        var mx = 0d;
        var wordsCount = 0;
        for (var item : map.entrySet()) {
            mx += (item.getKey() * item.getValue());
            wordsCount += item.getValue();
        }
        var mean = mx / wordsCount;

        var sum = 0d;
        for (var item : map.entrySet()) {
            sum += (Math.pow(item.getKey(), 2) * item.getValue());
        }

        var D = sum / wordsCount - Math.pow(mean, 2);
        var standardDeviation = Math.sqrt(D);

        long endTime = System.currentTimeMillis();

        System.out.println("Statistics:");
        System.out.printf("Word count: %s%n", wordSum);
        System.out.printf("Mean: %s%n", mean);
        System.out.printf("Dispersion: %s%n", D);
        System.out.printf("Standard deviation: %s%n", standardDeviation);
        System.out.printf("Time taken: %s%n", endTime - startTime);
    }
}
