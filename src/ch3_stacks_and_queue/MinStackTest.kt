package ch3_stacks_and_queue

import org.junit.jupiter.api.Assertions.*

internal class MinStackTest {

    @org.junit.jupiter.api.Test
    fun pop() {
        val minStack = MinStack()
        assertNull(minStack.pop())
        minStack.push(3)
        assertEquals(3, minStack.pop())
    }

    @org.junit.jupiter.api.Test
    fun push() {
        val minStack = MinStack()
        minStack.push(3)
        assertEquals(3, minStack.peek())
    }

    @org.junit.jupiter.api.Test
    fun peek() {
        val minStack = MinStack()
        assertNull(minStack.peek())
        minStack.push(3)
        minStack.push(5)
        assertEquals(5, minStack.peek())
        minStack.pop()
        assertEquals(3, minStack.peek())
    }

    @org.junit.jupiter.api.Test
    fun isEmpty() {
        val minStack = MinStack()
        assertTrue(minStack.isEmpty())
        minStack.push(5)
        assertFalse(minStack.isEmpty())
        minStack.pop()
        assertTrue(minStack.isEmpty())
    }

    @org.junit.jupiter.api.Test
    fun min() {
        val minStack = MinStack()
        assertEquals(Int.MAX_VALUE, minStack.min())
        minStack.push(5)
        assertEquals(5, minStack.min())
        minStack.push(2)
        assertEquals(2, minStack.min())
        minStack.push(3)
        assertEquals(2, minStack.min())
    }
}