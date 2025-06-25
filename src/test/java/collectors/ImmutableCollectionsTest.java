package collectors;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutableCollectionsTest {
    private final ImmutableCollections demo = new ImmutableCollections();

    @Test
    public void createImmutableList() {
        List<String> list = demo.createImmutableList("this", "is", "a", "list");
        assertEquals(4, list.size());
        assertEquals("this", list.get(0));
        assertEquals("is",   list.get(1));
        assertEquals("a",    list.get(2));
        assertEquals("list", list.get(3));
        assertThrows(UnsupportedOperationException.class, () -> list.add("extra"));
    }

    @Test
    public void createImmutableListJava7() {
        List<String> list = demo.createImmutableListJava7("this", "is", "a", "list");
        assertEquals(4, list.size());
        assertEquals("this", list.get(0));
        assertEquals("is",   list.get(1));
        assertEquals("a",    list.get(2));
        assertEquals("list", list.get(3));
        assertThrows(UnsupportedOperationException.class, () -> list.add("extra"));
    }

    @Test
    public void createImmutableSet() {
        Set<String> set = demo.createImmutableSet("a", "b", "b", "c");
        assertEquals(3, set.size());
        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
        assertTrue(set.contains("c"));
        assertThrows(UnsupportedOperationException.class, () -> set.add("d"));
    }

    @Test
    public void createImmutableSetJava7() {
        Set<String> set = demo.createImmutableSetJava7("a", "b", "b", "c");
        assertEquals(3, set.size());
        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
        assertTrue(set.contains("c"));
        assertThrows(UnsupportedOperationException.class, () -> set.add("d"));
    }

    @Test
    public void immutableMap() {
        assertThrows(UnsupportedOperationException.class, () -> demo.map.put("a", 5));
    }

}