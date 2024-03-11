package com.rootbalance.yleetodolist

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.rootbalance.yleetodolist.model.Todo
import com.rootbalance.yleetodolist.model.TodoDao
import com.rootbalance.yleetodolist.model.TodoDatabase
import com.rootbalance.yleetodolist.model.TodoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoRepositoryTest {
    private lateinit var appDatabase: TodoDatabase
    private lateinit var dao: TodoDao
    private lateinit var repository: TodoRepository

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase =
            Room.inMemoryDatabaseBuilder(
                appContext,
                TodoDatabase::class.java,
            ).build()
        dao = appDatabase.todoDao()
        repository = TodoRepository(dao)
    }

    @After
    fun cleanup() {
        appDatabase.close()
    }

    @Test
    fun insertCheckSize() =
        runBlocking {
            val insertItem = Todo(todo = "test", isVisible = true)
            repository.insert(insertItem)
            repository.insert(insertItem)

            val resultList = repository.getAllData()
            assertEquals(2, resultList.size)
        }

    @Test
    fun insertCheckValue() =
        runBlocking {
            val insertItem1 = Todo(todo = "test1", isVisible = true)
            val insertItem2 = Todo(todo = "test2", isVisible = false)
            repository.insert(insertItem1)
            repository.insert(insertItem2)

            val resultList = repository.getAllData()
            assertEquals(insertItem1.todo, resultList[0].todo)
            assertEquals(insertItem1.isVisible, resultList[0].isVisible)
            assertEquals(insertItem2.todo, resultList[1].todo)
            assertEquals(insertItem2.isVisible, resultList[1].isVisible)
        }

    @Test
    fun checkDefaultValue() =
        runBlocking {
            val insertItem = Todo(todo = "test")
            repository.insert(insertItem)

            val resultList = repository.getAllData()
            assert(resultList.first().isVisible)
        }

    @Test
    fun checkUidAutoGenerate() =
        runBlocking {
            val insertItem1 = Todo(todo = "test1", isVisible = true)
            val insertItem2 = Todo(todo = "test2", isVisible = false)
            repository.insert(insertItem1)
            repository.insert(insertItem2)

            val resultList = repository.getAllData()
            assertEquals(1, resultList[0].uid)
            assertEquals(2, resultList[1].uid)
        }

    @Test
    fun checkDeleteItem() =
        runBlocking {
            val insertItem1 = Todo(todo = "test1", isVisible = true)
            val insertItem2 = Todo(todo = "test2", isVisible = false)
            val insertItem3 = Todo(todo = "test3", isVisible = true)
            repository.insert(insertItem1)
            repository.insert(insertItem2)
            repository.insert(insertItem3)
            delay(100)

            val resultList = repository.getAllData()
            delay(100)
            assertEquals(3, resultList.size)

            repository.delete(resultList[1])
            delay(100)

            val deleteResultList = repository.getAllData()
            delay(100)
            assertEquals(2, deleteResultList.size)
            assertEquals(insertItem1.todo, deleteResultList[0].todo)
            assertEquals(insertItem1.isVisible, deleteResultList[0].isVisible)
            assertEquals(insertItem3.todo, deleteResultList[1].todo)
            assertEquals(insertItem3.isVisible, deleteResultList[1].isVisible)
        }

    @Test
    fun checkDeleteAllItems() =
        runBlocking {
            val insertItem1 = Todo(todo = "test1", isVisible = true)
            val insertItem2 = Todo(todo = "test2", isVisible = false)
            val insertItem3 = Todo(todo = "test3", isVisible = true)
            repository.insert(insertItem1)
            repository.insert(insertItem2)
            repository.insert(insertItem3)
            delay(100)

            val resultList = repository.getAllData()
            delay(100)
            assertEquals(3, resultList.size)

            repository.deleteAll()
            delay(100)

            val deleteResultList = repository.getAllData()
            delay(100)
            assertEquals(0, deleteResultList.size)
        }
}
