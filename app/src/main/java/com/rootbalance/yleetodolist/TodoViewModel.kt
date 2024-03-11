package com.rootbalance.yleetodolist

import androidx.lifecycle.ViewModel
import com.rootbalance.yleetodolist.model.Todo
import com.rootbalance.yleetodolist.model.TodoRepository

class TodoViewModel(private val repository: TodoRepository) : ViewModel() {
    val allList: List<Todo>
        get() = repository.getAllData()
}
