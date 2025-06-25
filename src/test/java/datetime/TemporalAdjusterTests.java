package datetime;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemporalAdjusterTests {
    @Test
    public void adjusters() {
        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        LocalDateTime end = start.with(TemporalAdjusters.firstDayOfNextMonth());
        assertEquals("2017-03-01T11:30", end.toString());

        end = start.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        assertEquals("2017-02-09T11:30", end.toString());

        end = start.with(TemporalAdjusters.previousOrSame(DayOfWeek.THURSDAY));
        assertEquals("2017-02-02T11:30", end.toString());
    }
}
