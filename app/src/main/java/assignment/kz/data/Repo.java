package assignment.kz.data;

import java.util.List;

import assignment.kz.data.db.entity.DbRecent;
import rx.Observable;

public interface Repo {
//
//    public Observable<Response<ContentResponse<Animal>>> getAnimalById(String id);
//    public Observable<LoginResponse> loginUser(String loginData);
//    public Observable<retrofit2.Response<ContentResponse<Object>>> registerAnimal(Animal animal);
//    public Observable<retrofit2.Response<ContentResponse<Object>>> insertAnimalAction(ActionAnimal actionAnimal);
//    public Observable<retrofit2.Response<ContentResponse<List<Location>>>> getLocations(int level, int parent);
//    public void savePostIndex(String postIndex);
//    public void saveSpinnerPosition(int position);
//    public int getSpinnerPosition();
//
//
//    public String getPostIndex();
//
//
//    public String getAccessToken();
//
//
//    public void putAccessToken(String accessToken);

    Observable<List<DbRecent>> getRecents();
    void insertRecent(String value);
    Observable<String> insertRecent(DbRecent dbRecent);

}
