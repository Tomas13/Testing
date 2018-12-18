package assignment.kz.data.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import assignment.kz.data.db.entity.DbRecent;

@Dao
public interface RecentDao {

    @Insert
    void insert(DbRecent... recent);

    @Update
    void update(DbRecent... recent);

    @Delete
    void delete(DbRecent... recent);

    @Query("SELECT * FROM recent")
    List<DbRecent> getSuggestions();

}
