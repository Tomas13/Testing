package com.example.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "suggestions")
data class SuggestionsDbEntity(
        @PrimaryKey var id: Int = 1,
        @ColumnInfo(name = "text") var text: String = ""
)