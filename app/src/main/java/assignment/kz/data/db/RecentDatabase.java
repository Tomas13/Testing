package assignment.kz.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import assignment.kz.data.db.dao.RecentDao;
import assignment.kz.data.db.entity.DbRecent;

@Database(entities = {DbRecent.class},
        version = 1)
public abstract class RecentDatabase extends RoomDatabase {

    private static RecentDatabase instance;

    public abstract RecentDao getRecentDao();

    public static RecentDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RecentDatabase.class,
                    "recent.sqlite")
                    .build();
        }

        return instance;
    }
}
