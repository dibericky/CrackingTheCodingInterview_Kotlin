package intro

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BCRTest{
    @Test
    fun findElementTest() {
        fun testWithData(arrA: IntArray, arrB: IntArray, result: Int, message: String) {
            kotlin.test.assertEquals(result, BCR.numElementCommonRecV1(arrA, arrB), "$message RecV1")
            kotlin.test.assertEquals(result, BCR.numElementCommonRecV2(arrA, arrB), "$message RecV2")
            kotlin.test.assertEquals(result, BCR.numElementInCommonIterative(arrA, arrB), "$message Iterative")
        }

        testWithData(intArrayOf(1), intArrayOf(2), 0, "Failed [1], [2]")
        testWithData(intArrayOf(1), intArrayOf(1), 1, "Failed [1], [1]")
        testWithData(intArrayOf(1, 4), intArrayOf(2, 3), 0, "Failed [1,4], [2,3]")
        testWithData(intArrayOf(1, 2), intArrayOf(2, 3), 1, "Failed [1,2], [2,3]")
        testWithData(intArrayOf(2,4,5,7,8,9), intArrayOf(3,4,6,7,9,10), 3, "Failed [2,4,5,7,8,9], [3,4,6,7,9,10]")
        testWithData(intArrayOf(13, 27, 35, 40, 49, 55, 59), intArrayOf(17, 35, 39, 40, 55, 58, 60), 3, "Failed long arrays")
    }
}