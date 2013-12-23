package loadster.sdk.exceptions;

import loadster.sdk.types.ErrorDetail;

/**
 * Exception that means something went wrong when communicating with the API.
 * This represents actual API error responses, not a lower-level problem such as a network
 * or protocol error.
 */
public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(ErrorDetail error) {
        super(error.getMessage());
    }
}
