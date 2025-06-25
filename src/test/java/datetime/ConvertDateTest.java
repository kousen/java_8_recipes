package datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class ConvertDateTest {
    private final ConvertDate cd = new ConvertDate();

    @Test
    public void convertFromDateToLD() {
        Date now = new Date();
        LocalDate localDate = cd.convertFromUtilDateToLocalDate(now);
        System.out.println(now + " " + localDate);
    }

    @Test
    public void convertFromDateToLDUsingString() {
        Date now = new Date();
        LocalDateTime localDateTime = cd.convertFromUtilDateToLDUsingString(now);
        System.out.println(now + " " + localDateTime);
    }



}