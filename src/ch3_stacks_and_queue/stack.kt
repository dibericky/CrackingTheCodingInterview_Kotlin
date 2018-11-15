package ch3_stacks_and_queue

/**
A stack use LIFO (Last In, First Out) ordering.
Operations: pop(): Remove the top item from the stack
            push(item): Add an item to the top of the stack
            peek(): Return the top of the stack
            isEmpty(): Return true if and only if the stack is empty
A stack does not offer constant-time access to the ith item, but it does allow constant time
adds and removes.
 */
interface Stack<T>{
    fun pop() : T?
    fun push(item: T)
    fun peek() : T?
    fun isEmpty(): Boolean
}
class MyStack<T> : Stack<T>{
    class StackNode<T>(val data: T){
        var next: StackNode<T>? = null
    }

    private var top : StackNode<T>? = null

    override fun pop() : T?{
        val item = top?.data
        top = top?.next
        return item
    }
    override fun push(item: T){
        val t = StackNode(item)
        t.next = top
        top = t
    }
    override fun peek() = top?.data

    override fun isEmpty() = top == null
}


/*
EXERCISE
 */
/**
 * Question: Design a stack which, in addition tot push and pop, has a function min which returns
 * the minimum element? Push, pop and min should operate in O(1) time.
 *
 */

class MinStack : Stack<Int>{
    private val stack : Stack<Int> = MyStack()
    private val min : Stack<Int> = MyStack()

    override fun pop(): Int? {
        val actualMin = min.peek()
        val popItem = stack.pop()
        if(actualMin == popItem)min.pop()
        return popItem
    }

    override fun push(item: Int) {
        val actualMin = min()
        if(item < actualMin)min.push(item)

        stack.push(item)
    }

    override fun peek() = stack.peek()

    override fun isEmpty() = stack.isEmpty()

    fun min() = min.peek()?:Int.MAX_VALUE
}