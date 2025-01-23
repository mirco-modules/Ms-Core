package org.khasanof.core.result;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClientResponseException;

/**
 * @author Nurislom
 * @see org.khasanof.core.result
 * @since 10/15/2024 3:30 PM
 */
@Getter
@Setter
public class RestClientErrorData<T> extends BasicResultData<T> {

    private String message;
    private String statusText;
    private String responseBody;
    private HttpStatusCode statusCode;

    public RestClientErrorData(boolean success, T data, String responseText) {
        super(success, data, responseText);
    }

    public RestClientErrorData(T data, RestClientResponseException exception) {
        super(false, data, null);
        this.message = exception.getMessage();
        this.statusCode = exception.getStatusCode();
        this.statusText = exception.getStatusText();
        this.responseBody = exception.getResponseBodyAsString();
        super.setResponseText(createResponseText());
    }

    public RestClientErrorData(T data, RestClientErrorData restClientErrorData) {
        super(false, data, null);
        this.message = restClientErrorData.getMessage();
        this.statusCode = restClientErrorData.getStatusCode();
        this.statusText = restClientErrorData.getStatusText();
        this.responseBody = restClientErrorData.getResponseBody();
        super.setResponseText(createResponseText());
    }

    public String createResponseText() {
        return "Status: " + getStatusCode() + ", " +
                "StatusText: " + getStatusText() + ", " +
                "Message: " + getMessage() + ", " +
                "Response: " + getResponseBody();
    }
}
