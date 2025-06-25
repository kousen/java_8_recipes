package mapvsflatmap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WordMapTest {
    private final WordMap wordMap = new WordMap();
    private final Map<String, Long> sample = new HashMap<>();

    @BeforeEach
    public void setUp() {
        wordMap.setFileName("simple_file.txt");
        sample.put("some", 2L);
        sample.put("this", 2L);
        sample.put("simple", 2L);
        sample.put("with", 2L);
        sample.put("text", 2L);
        sample.put("duplicates", 1L);
        sample.put("very", 1L);
        sample.put("a", 1L);
        sample.put("in", 1L);
        sample.put("is", 1L);
        sample.put("it", 1L);
        sample.put("the", 1L);
        sample.put("file", 1L);
        sample.put("and", 1L);
        sample.put("has", 1L);
    }

    @Test
    public void checkSampleFile() {
        Map<String, Long> map = this.wordMap.createMap();
        assertEquals(sample.size(), map.size());
        sample.forEach((word, count) -> {
            System.out.printf("%s: %d%n", word, count);
            assertTrue(map.containsKey(word));
            assertEquals(count, map.get(word));
        });
    }

}