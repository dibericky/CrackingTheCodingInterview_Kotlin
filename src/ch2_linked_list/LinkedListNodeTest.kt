package ch2_linked_list

import ch2_linked_list.LinkedListNode.Companion.connectInLoop
import ch2_linked_list.LinkedListNode.Companion.count
import ch2_linked_list.LinkedListNode.Companion.createLinkedListFromArray
import ch2_linked_list.LinkedListNode.Companion.findMergePoint
import ch2_linked_list.LinkedListNode.Companion.getInitLoop
import ch2_linked_list.LinkedListNode.Companion.getKthToLast
import ch2_linked_list.LinkedListNode.Companion.getLast
import ch2_linked_list.LinkedListNode.Companion.getMeetingNode
import ch2_linked_list.LinkedListNode.Companion.hasLoop
import ch2_linked_list.LinkedListNode.Companion.pointerAtSameLength
import org.junit.jupiter.api.Assertions.*

internal class LinkedListNodeTest{
    @org.junit.jupiter.api.Test
    fun getInitLoopTest() {
        val list = createLinkedListFromArray(intArrayOf(1, 2, 3))
        val loop = createLinkedListFromArray(intArrayOf(4, 5, 6))
        connectInLoop(list!!, loop!!)

        kotlin.test.assertEquals(getInitLoop(list)?.data, 3, "Failed Init loop 3")
    }
    @org.junit.jupiter.api.Test
    fun getMeetingNodeTest() {
        val list = createLinkedListFromArray(intArrayOf(1, 2, 3))
        val loop = createLinkedListFromArray(intArrayOf(4, 5, 6))
        connectInLoop(list!!, loop!!)

        kotlin.test.assertEquals(getMeetingNode(list)?.data, 5, "Failed meeting node 5")
    }
    @org.junit.jupiter.api.Test
    fun hasLoopTest() {
        val list = createLinkedListFromArray(intArrayOf(1, 2, 3))
        val loop = createLinkedListFromArray(intArrayOf(4, 5, 6))
        connectInLoop(list!!, loop!!)

        kotlin.test.assertTrue(hasLoop(list), "Failed has loop")
    }
    @org.junit.jupiter.api.Test
    fun getKthToLastTest() {
        val list = createLinkedListFromArray(intArrayOf(1, 2, 3, 4, 5, 6))

        kotlin.test.assertEquals(5, getKthToLast(list!!, 1)?.data, "Failed k=1")
        kotlin.test.assertEquals(6, getKthToLast(list, 0)?.data, "Failed k=0")
        kotlin.test.assertNull(getKthToLast(list, 10)?.data, "Failed null")
        kotlin.test.assertEquals(3, getKthToLast(list, 3)?.data, "Failed k=3")
    }
    @org.junit.jupiter.api.Test
    fun pointerAtTheSameLengthTest() {
        val list = createLinkedListFromArray(intArrayOf(1, 2, 3, 4, 5, 6))
        val listB = createLinkedListFromArray(intArrayOf(2, 3, 4, 5, 6))

        val (a, b) = pointerAtSameLength(list!!, listB!!)
        assert(count(a) == count(b))
    }
    @org.junit.jupiter.api.Test
    fun findMergeTest() {
        val list = createLinkedListFromArray(intArrayOf(1, 2, 3, 4, 5, 6))
        val listB = createLinkedListFromArray(intArrayOf(2, 3, 4, 5, 6))

        //create merge
        val merge = createLinkedListFromArray(intArrayOf(7, 8, 9))
        getLast(list!!).next = merge
        getLast(listB!!).next = merge

        kotlin.test.assertEquals(merge, findMergePoint(list, listB), "Failed equals")
        kotlin.test.assertNotEquals(list, findMergePoint(list, listB), "Failed different")
    }

}