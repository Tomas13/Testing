package com.example.data

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Single

@Dao
interface SuggestionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(query: String)

    @Query("SELECT * FROM suggestions")
    fun getSuggestions(): Single<List<String>>?

}