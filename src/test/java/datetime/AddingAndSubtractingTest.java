package datetime;

import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddingAndSubtractingTest {
    @Test
    public void localDatePlus() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.of(2017, Month.FEBRUARY, 2);

        LocalDate end = start.plusDays(3);
        assertEquals("2017-02-05", end.format(formatter));

        end = start.plusWeeks(5);
        assertEquals("2017-03-09", end.format(formatter));

        end = start.plusMonths(7);
        assertEquals("2017-09-02", end.format(formatter));

        end = start.plusYears(2);
        assertEquals("2019-02-02", end.format(formatter));
    }

    @Test
    public void localDateMinus() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.of(2017, Month.FEBRUARY, 2);

        LocalDate end = start.minusDays(3);
        assertEquals("2017-01-30", end.format(formatter));

        end = start.minusWeeks(5);
        assertEquals("2016-12-29", end.format(formatter));

        end = start.minusMonths(7);
        assertEquals("2016-07-02", end.format(formatter));

        end = start.minusYears(2);
        assertEquals("2015-02-02", end.format(formatter));
    }

    @Test
    public void localTimePlus() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

        LocalTime start = LocalTime.of(11, 30, 0, 0);

        LocalTime end = start.plusNanos(1_000_000);
        assertEquals("11:30:00.001", end.format(formatter));

        end = start.plusSeconds(20);
        assertEquals("11:30:20", end.format(formatter));

        end = start.plusMinutes(45);
        assertEquals("12:15:00", end.format(formatter));

        end = start.plusHours(5);
        assertEquals("16:30:00", end.format(formatter));
    }

    @Test
    public void localTimeMinus() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

        LocalTime start = LocalTime.of(11, 30, 0, 0);

        LocalTime end = start.minusNanos(1_000_000);
        assertEquals("11:29:59.999", end.format(formatter));

        end = start.minusSeconds(20);
        assertEquals("11:29:40", end.format(formatter));

        end = start.minusMinutes(45);
        assertEquals("10:45:00", end.format(formatter));

        end = start.minusHours(5);
        assertEquals("06:30:00", end.format(formatter));
    }

    @Test
    public void plus_minus() {
        Period period = Period.of(2, 3, 4); // 2 years, 3 months, 4 days
        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        LocalDateTime end = start.plus(period);
        assertEquals("2019-05-06T11:30:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.plus(3, ChronoUnit.HALF_DAYS);
        assertEquals("2017-02-03T23:30:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.minus(period);
        assertEquals("2014-10-29T11:30:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.minus(2, ChronoUnit.CENTURIES);
        assertEquals("1817-02-02T11:30:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.plus(3, ChronoUnit.MILLENNIA);
        assertEquals("5017-02-02T11:30:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @Test
    public void with() {
        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        LocalDateTime end = start.withMinute(45);
        assertEquals("2017-02-02T11:45:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.withHour(16);
        assertEquals("2017-02-02T16:30:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.withDayOfMonth(28);
        assertEquals("2017-02-28T11:30:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.withDayOfYear(300);
        assertEquals("2017-10-27T11:30:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        end = start.withYear(2020);
        assertEquals("2020-02-02T11:30:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    @Test
    public void withInvalidDate() {
        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        assertThrows(DateTimeException.class, () -> start.withDayOfMonth(29));
    }

    @Test
    public void temporalField() {
        LocalDateTime start = LocalDateTime.of(2017, Month.JANUARY, 31, 11, 30);
        LocalDateTime end = start.with(ChronoField.MONTH_OF_YEAR, 2);
        assertEquals("2017-02-28T11:30:00", end.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}