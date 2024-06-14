package base.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpResponse;

public class HttpRestRequestException extends IOException {
  private HttpResponse<?> response;

  public HttpRestRequestException() {
    super();
  }

  public HttpRestRequestException(String message) {
    super(message);
  }

  public HttpRestRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  public HttpRestRequestException(HttpResponse<?> response) {
    this(response.body() != null ? response.body().toString() : null);
    this.response = response;
  }

  public HttpResponse<?> getResponse() {
    return response;
  }

  public void setResponse(HttpResponse<?> response) {
    this.response = response;
  }

  @Override
  public String toString() {
    URI requestUri = response.request().uri();
    int statusCode = response.statusCode();
    String reasonPhrase = response.body() != null ? response.body().toString() : "No Reason Phrase";
    String responseContent = "";
    try {
      responseContent = response.body().toString();
    } catch (Exception e) {
      responseContent = "Unable to read response content";
    }

    return requestUri
        + " - "
        + statusCode
        + " - "
        + reasonPhrase
        + System.lineSeparator()
        + responseContent
        + System.lineSeparator()
        + super.toString();
  }
}
