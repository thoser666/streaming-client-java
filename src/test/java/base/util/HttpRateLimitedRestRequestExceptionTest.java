package base.util;

import org.junit.jupiter.api.Test;

import java.net.http.HttpHeaders;
import java.net.http.HttpResponse;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

class HttpRateLimitedRestRequestExceptionTest {

  @Test
  void test_constructor_initializes_rateLimitBucket_from_response_header() {
    HttpResponse<?> response = mock(HttpResponse.class);
    HttpHeaders headers = mock(HttpHeaders.class);
    when(response.headers()).thenReturn(headers);
    when(headers.firstValue(HttpRequestRateLimits.MIXER_RATE_LIMIT_RESET_HEADER))
        .thenReturn(Optional.of("12345"));

        HttpRateLimitedRestRequestException exception = new HttpRateLimitedRestRequestException(response);

        assertEquals("12345", exception.getRateLimitBucket());
    }

//    @Test
//    public void test_set_rate_limit_bucket_with_valid_string() {
//        HttpResponse<?> response = mock(HttpResponse.class);
//        HttpRateLimitedRestRequestException exception = new HttpRateLimitedRestRequestException(response);
//        String validBucket = "validBucket";
//        exception.setRateLimitBucket(validBucket);
//        assertEquals(validBucket, exception.getRateLimitBucket());
//    }
}