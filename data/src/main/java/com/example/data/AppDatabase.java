package com.example.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {PhotoDbEntity.class}, version = 1)
abstract class AppDatabase extends RoomDatabase {
  public abstract PhotoDao photoDao();
//  public abstract SuggestionsDao suggestionsDao();
}
