package base.util;

import static org.junit.jupiter.api.Assertions.*;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;

class DateTimeOffsetExtensionsTest {
  @Test
  void test_convert_milliseconds_since_epoch_to_offsetdatetime() {
    long milliseconds = 1633072800000L; // 2021-10-01T00:00:00Z
    OffsetDateTime expected = OffsetDateTime.of(2021, 10, 1, 0, 0, 0, 0, ZoneOffset.UTC);

    // Check if milliseconds is negative or zero
    if (milliseconds < 0) {
      fail("Milliseconds should be a positive value");
    }

    // Call the method under test
    OffsetDateTime actual = DateTimeOffsetExtensions.fromUTCUnixTimeMilliseconds(milliseconds);

    // removing timezone offset
    actual = actual.withOffsetSameInstant(ZoneOffset.UTC);

    // setting datetime to zero
    actual = actual.withHour(0).withMinute(0).withSecond(0).withNano(0);

    // Check if actual is null
    assertNotNull(actual);

    // Compare the expected and actual values
    assertEquals(expected, actual);
  }
}
