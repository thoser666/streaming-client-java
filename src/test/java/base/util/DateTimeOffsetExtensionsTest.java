package base.util;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeOffsetExtensionsTest {
    @Test
    public void test_convert_milliseconds_since_epoch_to_offsetdatetime() {
        long milliseconds = 1633072800000L; // 2021-10-01T00:00:00Z
        OffsetDateTime expected = OffsetDateTime.of(2021, 10, 1, 0, 0, 0, 0, ZoneOffset.UTC);
        OffsetDateTime actual = DateTimeOffsetExtensions.fromUTCUnixTimeMilliseconds(milliseconds);
        assertEquals(expected, actual);
    }

    @Test
    public void test_handle_negative_milliseconds_since_epoch() {
        long milliseconds = -1633072800000L; // Before epoch time
        OffsetDateTime expected = OffsetDateTime.of(1918, 3, 4, 0, 0, 0, 0, ZoneOffset.UTC);
        OffsetDateTime actual = DateTimeOffsetExtensions.fromUTCUnixTimeMilliseconds(milliseconds);
        assertEquals(expected, actual);
    }

}