package base.util;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class DateTimeOffsetExtensions {
  public static OffsetDateTime fromUTCUnixTimeMilliseconds(long milliseconds) {
    return OffsetDateTime.ofInstant(
        java.time.Instant.ofEpochMilli(milliseconds),
        ZoneOffset.ofTotalSeconds(OffsetDateTime.now().getOffset().getTotalSeconds()));
  }

  public static OffsetDateTime fromUTCUnixTimeSeconds(long seconds) {
    return OffsetDateTime.ofInstant(
        java.time.Instant.ofEpochSecond(seconds),
        ZoneOffset.ofTotalSeconds(OffsetDateTime.now().getOffset().getTotalSeconds()));
  }

  public static OffsetDateTime fromUTCISO8601String(String dateTime) {
    return OffsetDateTime.parse(dateTime)
        .withOffsetSameInstant(
            ZoneOffset.ofTotalSeconds(OffsetDateTime.now().getOffset().getTotalSeconds()));
  }

  public static String toUTCISO8601String(OffsetDateTime dateTime) {
    return dateTime.withOffsetSameInstant(ZoneOffset.UTC).toString();
  }

  public static String toRFC3339String(OffsetDateTime dateTime) {
    return toUTCISO8601String(dateTime) + "Z";
  }
}
