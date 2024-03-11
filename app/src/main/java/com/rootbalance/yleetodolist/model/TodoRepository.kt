package com.rootbalance.yleetodolist.model

class TodoRepository(private val todoDao: TodoDao) {
    fun getAllData(): List<Todo> = todoDao.getAll()

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }

    suspend fun delete(todo: Todo) {
        todoDao.delete(todo)
    }

    suspend fun deleteAll() {
        todoDao.deleteAll()
    }
}
