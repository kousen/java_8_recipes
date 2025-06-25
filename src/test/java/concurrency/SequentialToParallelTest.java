package concurrency;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SequentialToParallelTest {
    @Test
    public void sequentialStreamOf() {
        assertFalse(Stream.of(3, 1, 4, 1, 5, 9).isParallel());
    }

    @Test
    public void sequentialIterateStream() {
        assertFalse(Stream.iterate(1, n -> n + 1).isParallel());
    }

    @Test
    public void sequentialGenerateStream() {
        assertFalse(Stream.generate(Math::random).isParallel());
    }

    @Test
    public void sequentialCollectionStream() {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        assertFalse(numbers.stream().isParallel());
    }

    @Test
    public void parallelMethodOnStream() {
        assertTrue(Stream.of(3, 1, 4, 1, 5, 9)
                .parallel()
                .isParallel());
    }

    @Test
    public void parallelStreamMethodOnCollection() {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        assertTrue(numbers.parallelStream().isParallel());
    }

    @Test
    public void parallelStreamThenSequential() {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        assertFalse(numbers.parallelStream()
                .sequential()
                .isParallel());
    }

    @Test
    public void switchingParallelToSequentialInSameStream() {
        List<Integer> numbers = Arrays.asList(3, 1, 4, 1, 5, 9);
        List<Integer> nums = numbers.parallelStream()
                .map(n -> n * 2)
                .peek(n -> System.out.printf("%s processing %d%n", Thread.currentThread().getName(), n))
                .sequential()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(nums);
    }
}