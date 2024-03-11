package com.rootbalance.yleetodolist.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Query("SELECT * FROM todolist ORDER BY uid ASC")
    fun getAll(): List<Todo>

    @Insert
    suspend fun insertItems(vararg todos: Todo)

    @Insert
    suspend fun insert(todo: Todo)

    @Delete
    suspend fun delete(vararg todo: Todo)

    @Query("DELETE FROM todolist")
    suspend fun deleteAll()
}
