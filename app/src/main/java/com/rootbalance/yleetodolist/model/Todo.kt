package com.rootbalance.yleetodolist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todolist")
class Todo(
    @ColumnInfo(name = "todo") val todo: String,
    @ColumnInfo(name = "is_visible") val isVisible: Boolean = true,
) {
    @PrimaryKey(autoGenerate = true)
    var uid: Long = 0
}
