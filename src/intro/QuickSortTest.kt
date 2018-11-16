package intro

import intro.QuickSort.Companion.quickSort
import org.junit.jupiter.api.Test

internal class QuickSortTest{
    @Test
    fun quickSortTest(){
        val arr = intArrayOf(4,2,3,1,7,5,6)
        quickSort(arr)
        for(i in 0 until (arr.size-1)){
            assert(arr[i] < arr[i+1])
        }
    }
}