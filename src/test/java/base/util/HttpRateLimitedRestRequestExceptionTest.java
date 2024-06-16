package base.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class HttpRateLimitedRestRequestExceptionTest {

  @Test
  void test_constructor_initializes_rateLimitBucket_from_response_header() {
    HttpResponse<?> response = mock(HttpResponse.class);
    HttpHeaders headers = mock(HttpHeaders.class);
    when(response.headers()).thenReturn(headers);
    when(headers.firstValue(HttpRequestRateLimits.MIXER_RATE_LIMIT_RESET_HEADER))
        .thenReturn(Optional.of("12345"));

    HttpRateLimitedRestRequestException exception =
        new HttpRateLimitedRestRequestException(response);

    assertEquals("12345", exception.getRateLimitBucket());
  }


}
