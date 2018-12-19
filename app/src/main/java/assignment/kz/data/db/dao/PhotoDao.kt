package assignment.kz.data.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import assignment.kz.data.db.entity.DbPhotoEntity

@Dao
interface PhotoDao {
    @Insert()
    fun insertAll(photos: List<DbPhotoEntity>)

    @Query("SELECT * FROM photos WHERE id = :photoId LIMIT 1")
    fun loadPhotoById(photoId: String): DbPhotoEntity?
}
