package sorting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortingMapsTest {
    private final SortingMaps<String, Integer> sm = new SortingMaps<>();
    private final Map<String, Integer> map = new HashMap<>();

    @BeforeEach
    public void setUp() {
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 2);
        map.put("d", 1);
        map.put("e", 3);
        sm.setMap(map);
    }

    @Test
    public void getMapSortedByKey() {
        sm.getMapSortedByKey().keySet().stream()
            .reduce((prev, curr) -> {
                assertTrue(prev.compareTo(curr) <= 0);
                return curr;
            });
    }

    @Test
    public void getMapSortedByKeyDesc() {
        sm.getMapSortedByKeyDesc().keySet().stream()
                .reduce((prev, curr) -> {
                    assertTrue(prev.compareTo(curr) >= 0);
                    return curr;
                });
    }

    @Test
    public void getMapSortedByValue() {
        sm.getMapSortedByValue().values().stream()
                .reduce((prev, curr) -> {
                    assertTrue(prev.compareTo(curr) <= 0);
                    return curr;
                });
    }

    @Test
    public void getMapSortedByValueDesc() {
        Map<String, Integer> result = sm.getMapSortedByValueDesc();
        result.values().stream()
                .reduce((prev, curr) -> {
                    assertTrue(prev.compareTo(curr) >= 0);
                    return curr;
                });
    }

    private <K,V> void printMap(Map<K,V> m) {
        m.forEach((k,v) -> System.out.println(k + ": " + v));
    }

}