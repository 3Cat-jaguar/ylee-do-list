package com.rootbalance.yleetodolist.model

class TodoRepository(private val todoDao: TodoDao) {
    val allData: List<Todo> = todoDao.getAll()

    suspend fun insert(todo: Todo) {
        todoDao.insert(todo)
    }
}
