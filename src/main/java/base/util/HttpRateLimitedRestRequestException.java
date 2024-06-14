package base.util;

import java.net.http.HttpResponse;

public class HttpRateLimitedRestRequestException extends HttpRestRequestException {
    private String rateLimitBucket;
    private Object partialData;

    public HttpRateLimitedRestRequestException(HttpResponse<?> response) {
        super(response);
        this.rateLimitBucket = getHeaderValue(response, HttpRequestRateLimits.MIXER_RATE_LIMIT_RESET_HEADER);
    }

    public String getRateLimitBucket() {
        return rateLimitBucket;
    }

    public void setRateLimitBucket(String rateLimitBucket) {
        this.rateLimitBucket = rateLimitBucket;
    }

    public Object getPartialData() {
        return partialData;
    }

    public void setPartialData(Object partialData) {
        this.partialData = partialData;
    }

    private String getHeaderValue(HttpResponse<?> response, String headerName) {
        return response.headers().firstValue(headerName).orElse(null);
    }
}



