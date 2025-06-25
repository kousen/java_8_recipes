package streams;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeekDemoTest {
    private final PeekDemo demo = new PeekDemo();

    @Test
    public void sumUpTo() {
        assertEquals(55,   demo.sumUpTo(10));
        assertEquals(5050, demo.sumUpTo(100));
    }

    @Test
    public void sumEachDoubleUpTo() {
        assertEquals(2 * 55,   demo.sumEachDoubleUpTo(10));
        assertEquals(2 * 5050, demo.sumEachDoubleUpTo(100));
    }

    @Test
    public void sumDoublesDivisibleBy3() {
        assertEquals(1554, demo.sumDoublesDivisibleBy3(100, 120));
    }
}