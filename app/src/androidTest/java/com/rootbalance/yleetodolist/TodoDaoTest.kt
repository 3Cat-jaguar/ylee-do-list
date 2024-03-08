package com.rootbalance.yleetodolist

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.rootbalance.yleetodolist.model.Todo
import com.rootbalance.yleetodolist.model.TodoDao
import com.rootbalance.yleetodolist.model.TodoDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TodoDaoTest {
    private lateinit var appDatabase: TodoDatabase
    private lateinit var dao: TodoDao

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase =
            Room.inMemoryDatabaseBuilder(
                appContext,
                TodoDatabase::class.java,
            ).build()
        dao = appDatabase.todoDao()
    }

    @After
    fun cleanup() {
        appDatabase.close()
    }

    @Test
    fun insertCheckSize() =
        runBlocking {
            val insertItem = Todo(todo = "test", isVisible = true)
            dao.insert(insertItem)
            dao.insert(insertItem)

            val resultList = dao.getAll()
            assertEquals(2, resultList.size)
        }

    @Test
    fun insertCheckValue() =
        runBlocking {
            val insertItem1 = Todo(todo = "test1", isVisible = true)
            val insertItem2 = Todo(todo = "test2", isVisible = false)
            dao.insert(insertItem1)
            dao.insert(insertItem2)

            val resultList = dao.getAll()
            assertEquals(insertItem1.todo, resultList[0].todo)
            assertEquals(insertItem1.isVisible, resultList[0].isVisible)
            assertEquals(insertItem2.todo, resultList[1].todo)
            assertEquals(insertItem2.isVisible, resultList[1].isVisible)
        }

    @Test
    fun checkDefaultValue() =
        runBlocking {
            val insertItem = Todo(todo = "test")
            dao.insert(insertItem)

            val resultList = dao.getAll()
            assert(resultList.first().isVisible)
        }

    @Test
    fun checkUidAutoGenerate() =
        runBlocking {
            val insertItem1 = Todo(todo = "test1", isVisible = true)
            val insertItem2 = Todo(todo = "test2", isVisible = false)
            dao.insert(insertItem1)
            dao.insert(insertItem2)

            val resultList = dao.getAll()
            assertEquals(1, resultList[0].uid)
            assertEquals(2, resultList[1].uid)
        }
}
