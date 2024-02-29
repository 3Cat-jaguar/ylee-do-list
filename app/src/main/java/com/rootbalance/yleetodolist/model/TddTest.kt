package com.rootbalance.yleetodolist.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TddTest : ToDoDAO {
    override suspend fun delete(todo: Todo) {
//        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
//        TODO("Not yet implemented")
    }

    override fun getAll(): Flow<List<Todo>> {
//        TODO("Not yet implemented")
        return flow {
            emit(listOf(Todo(uid = 1, todo = "test", isVisible = true)))
        }
    }

    override fun findByContent(
        content: String,
        is_visible: Boolean,
    ): Flow<Todo> {
//        TODO("Not yet implemented")
        return flow {
            emit(Todo(uid = 1, todo = "test", isVisible = true))
        }
    }

    override suspend fun insertAll(vararg todos: Todo) {
//        TODO("Not yet implemented")
    }

    override fun loadAllByIds(userIds: IntArray): Flow<List<Todo>> {
//        TODO("Not yet implemented")
        return flow {
            emit(listOf(Todo(uid = 1, todo = "test", isVisible = true)))
        }
    }
}
