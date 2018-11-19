package ch4_trees_and_graphs

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class BinaryTreeVisitTest{
    private val visitData = mutableListOf<Int>()
    var visitTree : BinaryTreeVisit? = null
    var tree : TreeNode? = null

    @BeforeAll
    fun init(){
        visitTree = BinaryTreeVisit {
            visitData.add(it.value)
        }

        tree = TreeNode(
                TreeNode(
                        TreeNode(null, null, 2),
                        TreeNode(null, null, 4),
                        3
                ),
                TreeNode(
                        TreeNode(null, null, 6),
                        TreeNode(null, null, 8),
                        7
                ),
                5)
    }

    @BeforeEach
    fun clean(){
        visitData.clear()
    }

    @Test
    fun inOrderVisit() {
        val expected = listOf(2,3,4,5,6,7,8)
        visitTree?.inOrderVisit(tree)

        check(expected, visitData)
    }

    @Test
    fun preOrderVisit() {
        val expected = listOf(5,3,2,4,7,6,8)
        visitTree?.preOrderVisit(tree)

        check(expected, visitData)
    }

    @Test
    fun postOrderVisit() {
        val expected = listOf(2,4,3,6,8,7,5)
        visitTree?.postOrderVisit(tree)

        check(expected, visitData)
    }

    private fun check(expected: List<Int>, actual: List<Int>){
        for(i in 0 until actual.size){
            assertEquals(expected[i], actual[i])
        }
    }

}