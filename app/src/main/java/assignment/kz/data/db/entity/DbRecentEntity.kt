package assignment.kz.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey


@Entity(tableName = "recent",
        indices = [Index(value = ["value"], unique = true)])
data class DbRecentEntity(
        @ColumnInfo(name = "value")
        var value: String

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}