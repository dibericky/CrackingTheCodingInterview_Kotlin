package ch3_stacks_and_queue

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BetterQueueWithStackTest{

    @Test
    fun remove() {
        val queue = BetterQueueWithStack<Int>()
        queue.apply {
            assertNull(remove())
            add(3)
            add(5)
            add(6)
            assertEquals(3, remove())
            assertEquals(5, remove())
            assertEquals(6, remove())
            assertNull(remove())
        }
    }

    @Test
    fun peek() {
        val queue = BetterQueueWithStack<Int>()
        queue.apply {
            assertNull(peek())
            add(3)
            assertEquals(3, peek())
            add(4)
            assertEquals(3, peek()) //It's a queue!
        }

    }

    @Test
    fun isEmpty() {
        val queue = BetterQueueWithStack<Int>()
        queue.apply {
            assertTrue(isEmpty())
            add(3)
            assertFalse(isEmpty())
        }

    }
}