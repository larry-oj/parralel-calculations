package task4;

import common.Folder;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

public class TaskFour {
    private final static String[] wordsToSearch = {"exquisite", "as"};

    public static void main(String[] args) throws IOException {
        var filesPath = new File("src/files").getAbsolutePath();
        var file = new File(filesPath);
        var folder = Folder.fromDirectory(file);

        var wordCounter = new WordCounter();
        var occurrences = wordCounter.countOccurrencesInParallel(folder, wordsToSearch);

        System.out.printf("Total occurrences: %s%n", occurrences.size());

        for (var word : wordsToSearch) {
            var wordMatches = occurrences.stream()
                    .filter(occurrence -> Objects.equals(occurrence.word(), word));

            var list = wordMatches.filter(distinctByKey(Occurrence::file)).map(Occurrence::file).toList();
            if (list.size() == 0) continue;

            System.out.printf("'%s' was found in:%n", word);

            for (var fileName : list) {
                System.out.println(fileName);
            }
        }
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }
}
