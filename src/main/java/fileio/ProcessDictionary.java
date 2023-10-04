package fileio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ProcessDictionary {
    private final Path dictionary = Paths.get("src/main/resources/dict/words");

    public int maxLength() {
        try (Stream<String> words = Files.lines(dictionary)) {
            return words.max(Comparator.comparing(String::length))  // Optional<String>
                    .orElse("").length();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void printTenLongestWords() {
        System.out.println("\nTen Longest Words:");
        try (Stream<String> words = Files.lines(dictionary)) {
            words.filter(s -> s.length() > 20)
                    .sorted(Comparator.comparingInt(String::length).reversed()
                            //.thenComparing(Comparator.reverseOrder()))
                    )
                    .limit(10)
                    .forEach(w -> System.out.printf("%s (%d)%n", w, w.length()));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void printWordsOfEachLength() {
        System.out.println("\nList of words of each length:");
        try (Stream<String> words = Files.lines(dictionary)) {
            words.filter(s -> s.length() > 20)
                    .collect(groupingBy(String::length)) // Map<Integer,List<String>>
                    .forEach((len, wordList) -> System.out.println(len + ": " + wordList));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void printHowManyWordsOfEachLength() {
        System.out.println("\nNumber of words of each length:");
        try (Stream<String> words = Files.lines(dictionary)) {
            words.filter(s -> s.length() > 20)
                    .collect(groupingBy(String::length, counting())) // Map<Integer,Long>
                    .forEach((len, num) -> System.out.printf("%d: %d%n", len, num));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void printSortedMapOfWords() {
        System.out.println("\nNumber of words of each length (desc order):");
        try (Stream<String> words = Files.lines(dictionary)) {
            Map<Integer, Long> map = words.filter(s -> s.length() > 20)
                    .collect(groupingBy(String::length, counting()));

            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Length %d: %d words%n", e.getKey(), e.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printSortedMapOfWordsUsingBufferedReader() {
        System.out.println("\nNumber of words of each length (desc order):");
        try (Stream<String> words =
                     new BufferedReader(
                             new FileReader("src/main/resources/dict/words")).lines()) {
            Map<Integer, Long> map = words.filter(s -> s.length() > 20)
                    .collect(groupingBy(String::length, counting()));

            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                    .forEach(e -> System.out.printf("Length %d: %d words%n", e.getKey(), e.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ProcessDictionary processDictionary = new ProcessDictionary();
        processDictionary.printTenLongestWords();
        processDictionary.printWordsOfEachLength();
        processDictionary.printHowManyWordsOfEachLength();
        processDictionary.printSortedMapOfWords();
        processDictionary.printSortedMapOfWordsUsingBufferedReader();
    }
}
