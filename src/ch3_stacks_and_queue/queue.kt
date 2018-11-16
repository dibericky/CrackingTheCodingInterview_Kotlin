package ch3_stacks_and_queue

/**
 * A queue implements FIFO (First In, First Out) ordering.
 *
 * Operations:  add(item): Add an item to the end of the list
 *              remove(): Remove the first item in the list
 *              peek(): Return the top of the queue
 *              isEmpty(): Return true if and only if the queue is empty
 */
interface Queue<T>{
    fun add(item:T)
    fun remove():T?
    fun peek():T?
    fun isEmpty():Boolean
}
class MyQueue<T> : Queue<T>{
    private class QueueNode<T>(val data: T){
        var next: QueueNode<T>?=null
    }
    private var first: QueueNode<T>? = null
    private var last: QueueNode<T>? = null

    override fun add(item: T){
        val t = QueueNode(item)
        if(last != null) last!!.next = t
        last = t
        if(first == null){
            first = last
        }
    }
    override fun remove(): T?{
        val data = first?.data
        first = first?.next
        if(first==null)last=null
        return data
    }
    override fun peek():T?{
        return first?.data
    }
    override fun isEmpty() = first==null

}

/**
 * My Solution
 */
class MyQueueWithStack<T> : Queue<T>{
    private val stackA = MyStack<T>()
    private val stackSupp = MyStack<T>()

    override fun add(item: T) {
        stackA.push(item)
    }

    override fun remove(): T?{
        val popData = switchDataThen(source = stackA, destination = stackSupp){pop()}
        switchData(stackSupp, stackA)
        return popData
    }

    private fun switchData(source: MyStack<T>, destination: MyStack<T>){
        while(!source.isEmpty()){
            source.pop()?.let { destination.push(it) }
        }
    }
    private fun switchDataThen(source: MyStack<T>, destination: MyStack<T>, block: MyStack<T>.() -> T?) : T?{
        switchData(source, destination)
        return destination.block()
    }
    override fun peek(): T? {
        val peekData = switchDataThen(source = stackA, destination = stackSupp){peek()}
        switchData(stackSupp, stackA)
        return peekData
    }

    override fun isEmpty() = stackA.isEmpty()
}

/*
 Better implementation:
 s1 e s2 are the two stacks.
 We can implement a "lazy" approach where we let the elements sit in s2 until we absolutely must reverse the elemets.
 */
/**
 stackNewest has the newest elements on top and stackOldest has the oldest elements on top.
 When we dequeue an element, we want to remove the oldest element first, and so we dequeue from stackOldest.
 If stackOldest is empty, then we want to transfer all elements from stackNewest into this stack in reverse older.
 To insert an element, we push onto stackNewest, since it has the newest elements on top.
 */
/**
 * My Solution
 */
class BetterQueueWithStack<T> : Queue<T>{
    private val stackNewest = MyStack<T>()
    private val stackOldest = MyStack<T>()

    override fun add(item: T) {
        stackNewest.push(item)
    }

    override fun remove(): T? = switchDataIfNeededThen(stackNewest, stackOldest){pop()}


    private fun switchData(source: MyStack<T>, destination: MyStack<T>){
        while(!source.isEmpty()){
            source.pop()?.let { destination.push(it) }
        }
    }
    private fun switchDataIfNeededThen(source: MyStack<T>, destination: MyStack<T>, block: MyStack<T>.() -> T?) : T?{
        if(destination.isEmpty())switchData(source, destination)
        return destination.block()
    }
    override fun peek(): T? = switchDataIfNeededThen(stackNewest, stackOldest){peek()}

    override fun isEmpty() = stackNewest.isEmpty() && stackOldest.isEmpty()
}