package lambdas;

import org.junit.jupiter.api.Test;

import java.util.function.IntPredicate;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompositionDemoTest {

    @Test
    public void perfectSquares() {
        assertTrue(CompositionDemo.isPerfect(4));
        assertTrue(CompositionDemo.isPerfect(9));
        assertTrue(CompositionDemo.isPerfect(16));
        assertTrue(CompositionDemo.isPerfect(25));
        assertFalse(CompositionDemo.isPerfect(10));
        System.out.println("Perfect squares:");
        IntStream.rangeClosed(1, 200)
                .filter(CompositionDemo::isPerfect)
                .forEach(System.out::println);
    }

    @Test
    public void prime() {
        assertTrue(CompositionDemo.isPrime(2));
        assertTrue(CompositionDemo.isPrime(3));
        assertTrue(CompositionDemo.isPrime(5));
        assertTrue(CompositionDemo.isPrime(7));
        assertTrue(CompositionDemo.isPrime(11));
        assertTrue(CompositionDemo.isPrime(13));
        assertTrue(CompositionDemo.isPrime(17));
        assertFalse(CompositionDemo.isPrime(15));

        IntStream.rangeClosed(1, 200)
                .filter(CompositionDemo::isPrime)
                .forEach(System.out::println);
    }

    @Test
    public void triangular() {
        assertTrue(CompositionDemo.isTriangular(1));
        assertTrue(CompositionDemo.isTriangular(3));
        assertTrue(CompositionDemo.isTriangular(6));
        assertTrue(CompositionDemo.isTriangular(10));
        assertTrue(CompositionDemo.isTriangular(15));
        assertTrue(CompositionDemo.isTriangular(21));
        assertFalse(CompositionDemo.isTriangular(4));

        System.out.println("Triangle numbers: ");
        IntStream.rangeClosed(1, 200)
                .filter(CompositionDemo::isTriangular)
                .forEach(System.out::println);
    }

    @Test
    public void triangularAndPerfect() {
        IntPredicate triangular = CompositionDemo::isTriangular;
        IntPredicate perfect = CompositionDemo::isPerfect;
        IntPredicate both = triangular.and(perfect);
        assertTrue(both.test(1));
        assertTrue(both.test(36));
        assertTrue(both.test(1225));
        assertFalse(both.test(15));
        assertFalse(both.test(25));

        IntStream.rangeClosed(1, 10_000)
                .filter(both)
                .forEach(System.out::println);
    }
}