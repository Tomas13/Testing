package assignment.kz.data.network;

import assignment.kz.data.network.model.Response;
import rx.Observable;

public interface ApiHelper {
    Observable<Response> searchPhotos(String text);
}
