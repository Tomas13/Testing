package assignment.kz.data.network.utils;

/**
 * Main Response type. All API responses should come in this format, with the payload stored in
 * the `content` field.
 * @param <T> - type of payload.
 */
public class ContentResponse<T> {
    public T content;
    public Object messages;

    public Object getErrorMessage() {
        if (messages != null) {
            return messages;
        }
        return null;
    }
}
