package assignment.kz.data.network.utils;

/**
 * Thrown when a malformed response body is returned from the server.
 */
public class MalformedResponseException extends RuntimeException {

    public MalformedResponseException(String message) {
        super(message);
    }
}
