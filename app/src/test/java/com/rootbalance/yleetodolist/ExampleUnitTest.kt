package com.rootbalance.yleetodolist

import com.rootbalance.yleetodolist.model.TddTest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(3, 1 + 2)
    }

    @Test
    fun `test fetchData Flow`() =
        runBlocking {
            val testClass = TddTest()
            val result = testClass.findByContent("test", true)

            result.collect { todo ->
                assertEquals("hello", todo.todo)
            }
        }
}
