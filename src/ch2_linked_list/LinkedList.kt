package ch2_linked_list

import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

fun main(args: Array<String>) {
    pointerAtTheSameLengthTest()

    findMergeTest()

    getKthToLastTest()

    hasLoopTest()
    getMeetingNodeTest()
    getInitLoopTest()
}
/**
 * A LinkedList is a data structure that represent a sequences of nodes.
 * Unlike an array, a liked list does not provide constant time access to a particular index within the list.
 * This means that if you'd like to find the Kth element in the list, you will need to iterate through K elements.
 * The benefit of a linked list is that you can add and remove items from the beginning of the list in constant time.
 *
 * It's important to know if it's a singly linked list (only next node) or a doubly linked list (prev and next).
 */

/**
 * Singly LinkedList
 */
class Node(val data: Int) {
    var next: Node? = null

    fun appendToTail(d: Int) {
        val end = Node(d)
        var n: Node = this
        while (n.next != null) {
            n = n.next!!  //n.next cannot be null
        }
        n.next = end
    }
}

fun Node.hasNext() = this.next != null

fun deleteNode(head: Node, d: Int): Node? {
    var n = head
    if (n.data == d) return head.next

    while (n.next != null) {
        if (n.next?.data == d) {
            n.next = n.next?.next
            return head
        }
        n = n.next!! //n.next cannot be null
    }
    return head
}

/**
 * Given two lists of different lengths that merge at a point, determine the merge point
 */

fun findMergePoint(a: Node, b: Node): Node? {
    var (pA, pB) = pointerAtSameLength(a, b)

    while (pA != null || pB != null) {
        if (pA == pB) return pA
        pA = pA?.next
        pB = pB?.next
    }
    return null
}

fun pointerAtSameLength(a: Node, b: Node): Pair<Node?, Node?> {
    var lenA = count(a)
    var lenB = count(b)

    var pntrA: Node? = a
    var pntrB: Node? = b
    while (lenA > lenB) {
        pntrA = pntrA?.next
        lenA--
    }
    while (lenB > lenA) {
        pntrB = pntrB?.next
        lenB--
    }
    return Pair(pntrA, pntrB)
}

fun count(head: Node?): Int {
    if (head == null) return 0

    var n: Node = head
    var length = 1
    while (n.hasNext()) {
        length++
        n = n.next!!
    }
    return length
}

fun createLinkedListFromArray(content: IntArray): Node? {
    if (content.isEmpty()) return null
    val head = Node(content[0])
    var pointer: Node? = head
    for (i in 1 until content.size) {
        pointer?.next = Node(content[i])
        pointer = pointer?.next
    }
    return head
}

fun Node.printAllList() {
    var pointer: Node? = this
    while (pointer != null) {
        print("${pointer.data} ")
        pointer = pointer.next
    }
    println()
}

fun getKthToLast(head: Node, k: Int): Node? {
    fun getRecursive(node: Node?, k: Int, index: Int): Node? {
        if (index == k || node == null) return node
        return getRecursive(node.next, k, index - 1)
    }

    val length = count(head)
    return getRecursive(head, k, length - 1)

}

/**
 * Given a circular linked list, implement an algorithm that returns the node at the
 * beginning of the loop
 *
 * 'Given a circular linked list' so I could assume that the list has a loop.
 * I'll manage a more general case
 */
fun getInitLoop(head: Node) : Node?{
    val meeting = getMeetingNode(head) ?: return null

    var pntLoop = meeting
    var pnt = head
    while(pnt != pntLoop){
        pnt = pnt.next!! //I'm sure it's not null, because list has a loop!
        pntLoop = pntLoop.next!!
    }
    return pnt
}
fun getMeetingNode(head: Node) : Node?{
    if(head.next==null)return null

    var fast = head
    var slow = head
    while(fast.next?.next != null && slow.next != null){
        fast = fast.next?.next!!
        slow = slow.next!!
        if(fast == slow)return fast
    }
    return null
}
/**
 * Detect if a LinkedList has loop
 */
fun hasLoop(head: Node) = getMeetingNode(head) != null


fun getLast(a: Node): Node {
    if (a.next == null) return a
    return getLast(a.next!!)
}

fun connectInLoop(base: Node, loop: Node){
    val lastBase = getLast(base)
    val lastLoop = getLast(loop)
    lastBase.next = loop
    lastLoop.next = lastBase
}
/*
TEST
 */
fun getInitLoopTest(){
    val list = createLinkedListFromArray(intArrayOf(1, 2, 3))
    val loop = createLinkedListFromArray(intArrayOf(4,5,6))
    connectInLoop(list!!, loop!!)

    assertEquals(getInitLoop(list)?.data, 3, "Failed Init loop 3")
}
fun getMeetingNodeTest(){
    val list = createLinkedListFromArray(intArrayOf(1, 2, 3))
    val loop = createLinkedListFromArray(intArrayOf(4,5,6))
    connectInLoop(list!!, loop!!)

    assertEquals(getMeetingNode(list)?.data, 5, "Failed meeting node 5")
}
fun hasLoopTest(){
    val list = createLinkedListFromArray(intArrayOf(1, 2, 3))
    val loop = createLinkedListFromArray(intArrayOf(4,5,6))
    connectInLoop(list!!, loop!!)

    assertTrue(hasLoop(list), "Failed has loop")
}

fun getKthToLastTest() {
    val list = createLinkedListFromArray(intArrayOf(1, 2, 3, 4, 5, 6))

    assertEquals(5, getKthToLast(list!!, 1)?.data, "Failed k=1")
    assertEquals(6, getKthToLast(list, 0)?.data, "Failed k=0")
    assertNull(getKthToLast(list, 10)?.data, "Failed null")
    assertEquals(3, getKthToLast(list, 3)?.data, "Failed k=3")
}

fun pointerAtTheSameLengthTest() {
    val list = createLinkedListFromArray(intArrayOf(1, 2, 3, 4, 5, 6))
    val listB = createLinkedListFromArray(intArrayOf(2, 3, 4, 5, 6))

    val (a, b) = pointerAtSameLength(list!!, listB!!)
    assert(count(a) == count(b))
}

fun findMergeTest() {
    val list = createLinkedListFromArray(intArrayOf(1, 2, 3, 4, 5, 6))
    val listB = createLinkedListFromArray(intArrayOf(2, 3, 4, 5, 6))

    //create merge
    val merge = createLinkedListFromArray(intArrayOf(7, 8, 9))
    getLast(list!!).next = merge
    getLast(listB!!).next = merge

    assertEquals(merge, findMergePoint(list, listB), "Failed equals")
    assertNotEquals(list, findMergePoint(list, listB), "Failed different")
}
