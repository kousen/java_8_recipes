package objects;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ObjectsDemoTest {
    private final ObjectsDemo demo = new ObjectsDemo();

    @Test
    public void getStrings() {
        List<String> strings = Arrays.asList("this", "is", "a", "list", "of", "strings");
        assertTrue(Objects.deepEquals(strings, demo.getStrings()));
    }

    @Test
    public void getNonNullStrings() {
        List<String> strings = Arrays.asList("this", "is", "a", null, "list", "of", null, "strings");
        demo.setStrings(strings);
        assertTrue(Objects.deepEquals(
                Arrays.asList("this", "is", "a", "list", "of", "strings"),
                demo.getNonNullStrings()));
    }

    @Test
    public void getNonNullElements() {
        Random random = new Random();
        List<Double> doubles = random.doubles()
                .limit(10)
                .mapToObj(r -> r < 0.5 ? null : r)
                .collect(Collectors.toList());
        System.out.println(doubles);
        List<Double> nonNullElements = demo.getNonNullElements(doubles);
        System.out.println(nonNullElements);
        nonNullElements.forEach(r ->
            assertTrue(Objects.nonNull(r)));
    }
}