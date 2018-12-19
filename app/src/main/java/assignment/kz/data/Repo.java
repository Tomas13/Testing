package assignment.kz.data;

import java.util.List;

import assignment.kz.data.db.entity.DbPhotoEntity;
import assignment.kz.data.db.entity.DbRecentEntity;
import rx.Observable;

public interface Repo {
    Observable<List<DbRecentEntity>> getRecents();

    void insertRecent(String value);

    Observable<String> insertRecent(DbRecentEntity dbRecentEntity);

    Observable<String> insertAll(List<DbPhotoEntity> list);

    void insertPhotos(List<DbPhotoEntity> list);

    Observable<DbPhotoEntity> loadPhotoById(String photoId);
}
