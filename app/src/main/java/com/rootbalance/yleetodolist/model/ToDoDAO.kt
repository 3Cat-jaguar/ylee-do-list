package com.rootbalance.yleetodolist.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ToDoDAO {
    @Query("SELECT * FROM todolists")
    fun getAll(): Flow<List<Todo>>

    @Query("SELECT * FROM todolists WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): Flow<List<Todo>>

    @Query(
        "SELECT * FROM todolists WHERE todo LIKE :content AND is_visible LIKE :is_visible LIMIT 1",
    )
    fun findByContent(
        content: String,
        is_visible: Boolean,
    ): Flow<Todo>

    @Insert
    suspend fun insertAll(vararg todos: Todo)

    @Delete
    suspend fun delete(todo: Todo)

    @Query("DELETE FROM todolists")
    suspend fun deleteAll()
}
