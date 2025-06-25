package functionpackage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static functionpackage.ImplementPredicate.LENGTH_FIVE;
import static functionpackage.ImplementPredicate.STARTS_WITH_S;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImplementPredicateTest {
    private final ImplementPredicate demo = new ImplementPredicate();
    private String[] names;

    @BeforeEach
    public void setUp() {
        names = Stream.of("Mal", "Wash", "Kaylee", "Inara", "Zoë",
                "Jayne", "Simon", "River", "Shepherd Book")
                .sorted()
                .toArray(String[]::new);
    }

    @Test
    public void getNames() {
        String expected = "Inara, Jayne, Kaylee, Mal, River, Shepherd Book, Simon, Wash, Zoë";
        assertEquals(expected, demo.getNames(names));
    }

    @Test
    public void getNamesOfLength5() {
        assertEquals("Inara, Jayne, River, Simon", demo.getNamesOfLength(5, names));
    }

    @Test
    public void getNamesStartingWithS() {
        assertEquals("Shepherd Book, Simon", demo.getNamesStartingWith("S", names));
    }

    @Test
    public void getNamesSatisfyingCondition() {
        assertEquals("Inara, Jayne, River, Simon",
                demo.getNamesSatisfyingCondition(s -> s.length() == 5, names));
        assertEquals("Shepherd Book, Simon",
                demo.getNamesSatisfyingCondition(s -> s.startsWith("S"), names));
        assertEquals("Inara, Jayne, River, Simon",
                demo.getNamesSatisfyingCondition(LENGTH_FIVE, names));
        assertEquals("Shepherd Book, Simon",
                demo.getNamesSatisfyingCondition(STARTS_WITH_S, names));
    }

    @Test
    public void composedPredicate() {
        assertEquals("Simon",
                demo.getNamesSatisfyingCondition(
                        LENGTH_FIVE.and(STARTS_WITH_S), names));
        assertEquals("Inara, Jayne, River, Shepherd Book, Simon",
                demo.getNamesSatisfyingCondition(
                        LENGTH_FIVE.or(STARTS_WITH_S), names));
        assertEquals("Kaylee, Mal, Shepherd Book, Wash, Zoë",
                demo.getNamesSatisfyingCondition(LENGTH_FIVE.negate(), names));

    }

}