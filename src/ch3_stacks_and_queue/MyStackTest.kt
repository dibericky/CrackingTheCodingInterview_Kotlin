package ch3_stacks_and_queue

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MyStackTest {

    @Test
    fun pop() {
        val stack = MyStack<Int>()
        assertNull(stack.pop())
        stack.push(3)
        stack.push(5)
        assertEquals(5, stack.pop())
        assertEquals(3, stack.pop())
        assertNull(stack.pop())
    }

    @Test
    fun peek() {
        val stack = MyStack<Int>()
        assertNull(stack.peek())
        stack.push(3)
        assertEquals(3, stack.peek())
        stack.push(5)
        assertEquals(5, stack.peek())
    }

    @Test
    fun isEmpty() {
        val stack = MyStack<Int>()
        assertTrue(stack.isEmpty())
        stack.push(3)
        assertFalse(stack.isEmpty())
    }
}