package ch4_trees_and_graphs

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class MinHeapTest {
    private val visitData = mutableListOf<Int>()
    private var tree : TreeNode? = null

    @BeforeEach
    fun clean(){
        visitData.clear()
        tree = createMinHeap()
    }

    @Test
    fun insert() {
        val expected = listOf(2,50,55,90,4,87,7)

        val nodeToInsert = TreeNode(null, null, 2)
        MinHeap.insert(tree!!, nodeToInsert)

        check(expected, tree!!)
    }

    @Test
    fun extractMin() {
        val min = MinHeap.extractMin(tree!!)
        assertEquals(4, min)

        val expected = listOf(7, 50, 55, 90, 87)
        check(expected, tree!!)
    }

    private fun createMinHeap() : TreeNode{
        val root = TreeNode(null, null, 4)
        val left = TreeNode(
                TreeNode(null, null, 55),
                TreeNode(null, null, 90),
                50,
                root
        )
        val right = TreeNode(
                TreeNode(null, null, 87),
                null,
                7,
                root
        )
        root.left = left
        root.right = right
        left.left?.parent = left
        left.right?.parent = left
        right.left?.parent = right

        return root
    }

    private fun check(expected: List<Int>, root: TreeNode){
        BinaryTreeVisit{visitData.add(it.value)}.preOrderVisit(root)
        for(i in 0 until visitData.size){
            assertEquals(expected[i], visitData[i])
        }
    }
}