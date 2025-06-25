package datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaydayAdjusterTest {
    @Test
    public void payDay() {
        TemporalAdjuster adjuster = new PaydayAdjuster();
        IntStream.rangeClosed(1, 14)
                .mapToObj(day -> LocalDate.of(2017, Month.JULY, day))
                .forEach(date ->
                    assertEquals(14, date.with(adjuster).getDayOfMonth()));

        IntStream.rangeClosed(15, 31)
                .mapToObj(day -> LocalDate.of(2017, Month.JULY, day))
                .forEach(date ->
                        assertEquals(31, date.with(adjuster).getDayOfMonth()));
    }

    @Test
    public void payDayWithMethodRef() {
        IntStream.rangeClosed(1, 14)
                .mapToObj(day -> LocalDate.of(2017, Month.JULY, day))
                .forEach(date ->
                        assertEquals(14, date.with(Adjusters::adjustInto).getDayOfMonth()));

        IntStream.rangeClosed(15, 31)
                .mapToObj(day -> LocalDate.of(2017, Month.JULY, day))
                .forEach(date ->
                        assertEquals(31, date.with(Adjusters::adjustInto).getDayOfMonth()));
    }
}