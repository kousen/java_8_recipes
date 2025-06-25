package datetime;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegionIdsByOffsetTest {
    @Test
    public void getRegionNamesForGMT() {
        List<String> names = RegionIdsByOffset.getRegionNamesForOffset(0, 0);

        assertTrue(names.contains("GMT"));
        assertTrue(names.contains("Etc/GMT"));
        assertTrue(names.contains("Etc/UTC"));
        assertTrue(names.contains("UTC"));
        assertTrue(names.contains("Etc/Zulu"));
    }

    @Test
    public void getRegionNamesForNepal() {
        List<String> names = RegionIdsByOffset.getRegionNamesForOffset(5, 45);

        assertTrue(names.contains("Asia/Kathmandu"));
        assertTrue(names.contains("Asia/Katmandu"));
    }

    @Test
    public void getRegionNamesForNewYork() {
        ZoneId nyc = ZoneId.of("America/New_York");
        List<String> names = RegionIdsByOffset.getRegionNamesForZoneId(nyc);

        assertTrue(names.contains("America/New_York"));
        assertTrue(names.contains("US/Eastern"));
        assertTrue(names.contains("Etc/GMT+4") || names.contains("Etc/GMT+5"));
        assertTrue(names.contains("Canada/Eastern"));
    }

    @Test
    public void getRegionNamesForChicago() {
        ZoneId chicago = ZoneId.of("America/Chicago");
        List<String> names = RegionIdsByOffset.getRegionNamesForZoneId(chicago);

        assertTrue(names.contains("America/Chicago"));
        assertTrue(names.contains("US/Central"));
        assertTrue(names.contains("Canada/Central"));
        assertTrue(names.contains("Etc/GMT+5") || names.contains("Etc/GMT+6"));
    }

    @Test
    public void getRegionNamesForSystemDefault() {
        ZonedDateTime now = ZonedDateTime.now();
        ZoneId zoneId = now.getZone();
        List<String> names = RegionIdsByOffset.getRegionNamesForZoneId(zoneId);

        assertTrue(names.contains(zoneId.getId()));
    }
}