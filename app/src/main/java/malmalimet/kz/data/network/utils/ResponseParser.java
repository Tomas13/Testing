package malmalimet.kz.data.network.utils;

import retrofit2.Response;

/**
 * Parses ContentResponse from the API and returns their content, or throws
 * MalformedResponseException if response format is invalid.
 */
public class ResponseParser {
    public static <T> T getContentOrThrow(Response<ContentResponse<T>> response, String tag) {
        ContentResponse<T> body = response.body();
        if (body != null && body.content != null) {
            return body.content;
        } else {
            throw new MalformedResponseException("Malformed response: " + tag + ", Код: " + response.code());
        }
    }

    public static <T> T getMessage(Response<ContentResponse<T>> response, String tag) {
        ContentResponse<T> body = response.body();
        if (body != null && body.messages != null) {
            return (T) body.messages;
        } else {
            throw new MalformedResponseException("Malformed response: " + tag + ", Код: " + response.code());
        }
    }
}
