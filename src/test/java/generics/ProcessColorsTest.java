package generics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcessColorsTest {
    private final ProcessColors processColors = new ProcessColors();
    private final Color color = new Color(100, 50, 0);

    @BeforeEach
    public void setUp() {
        processColors.setColor(color);
    }

    @Test
    public void noProcessing() {
        Color c = processColors.applyFilter(color -> color);
        assertEquals(new Color(100, 50, 0), c);
    }

    @Test
    public void makeBrighter() {
        Color c = processColors.applyFilter(Color::brighter);
        assertEquals(new Color(142, 71, 0), c);
    }

    @Test
    public void makeDarker() {
        Color c = processColors.applyFilter(Color::darker);
        assertEquals(new Color(70, 35, 0), c);
    }

    @Test
    public void makeBrighterThenDarker() {
        Color c = processColors.applyFilters(
                Color::brighter, Color::darker);
        assertEquals(new Color(99, 49, 0), c);
    }
}