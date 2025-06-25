package datetime;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.util.stream.IntStream;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.NANOS;
import static java.time.temporal.TemporalQueries.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TemporalQueriesTests {
    @Test
    public void queries() {
        assertEquals(DAYS, LocalDate.now().query(precision()));
        assertEquals(NANOS, LocalTime.now().query(precision()));

        ZoneOffset offset = ZonedDateTime.now().getOffset();
        ZoneOffset query = ZonedDateTime.now().query(offset());
        assertEquals(offset, query);

        assertEquals(ZoneId.systemDefault(), ZonedDateTime.now().query(zone()));
        assertEquals(ZoneId.systemDefault(), ZonedDateTime.now().query(zoneId()));
    }

    private long daysUntilPirateDay(TemporalAccessor temporal) {
        int day = temporal.get(ChronoField.DAY_OF_MONTH);
        int month = temporal.get(ChronoField.MONTH_OF_YEAR);
        int year = temporal.get(ChronoField.YEAR);
        LocalDate date = LocalDate.of(year, month, day);
        LocalDate tlapd = LocalDate.of(year, Month.SEPTEMBER, 19);
        if (date.isAfter(tlapd)) {
            tlapd = tlapd.plusYears(1);
        }
        return ChronoUnit.DAYS.between(date, tlapd);
    }

    @Test
    public void pirateDay() {
        IntStream.range(10, 19)
                .mapToObj(n -> LocalDate.of(2017, Month.SEPTEMBER, n))
                .forEach(date ->
                        assertTrue(date.query(this::daysUntilPirateDay) <= 9));
        IntStream.rangeClosed(20, 30)
                .mapToObj(n -> LocalDate.of(2017, Month.SEPTEMBER, n))
                .forEach(date -> {
                    Long days = date.query(PirateQuery::daysUntilPirateDay);
                    assertTrue(days >= 354 && days < 365);
                });
    }
}
