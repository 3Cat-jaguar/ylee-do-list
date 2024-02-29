package com.rootbalance.yleetodolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todolists")
data class Todo(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "todo") val todo: String?,
    @ColumnInfo(name = "is_visible") val isVisible: Boolean = true,
)
