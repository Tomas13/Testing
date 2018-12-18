package assignment.kz.data.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "recent")
data class DbRecent(
        @ColumnInfo(name = "value")
        var value: String? = null

) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}