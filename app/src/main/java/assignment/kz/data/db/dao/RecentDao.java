package assignment.kz.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import assignment.kz.data.db.entity.DbRecentEntity;

@Dao
public interface RecentDao {

    @Insert
    void insert(DbRecentEntity... recent);

    @Update
    void update(DbRecentEntity... recent);

    @Delete
    void delete(DbRecentEntity... recent);

    @Query("SELECT * FROM recent")
    List<DbRecentEntity> getSuggestions();

}
