package ch1_arrays_and_string

import ch1_arrays_and_string.Strings.Companion.checkPermutation_bookSol
import ch1_arrays_and_string.Strings.Companion.checkPermutation_mySol
import ch1_arrays_and_string.Strings.Companion.isUnique_mySol
import org.junit.jupiter.api.Assertions.*

internal class StringsTest{
    @org.junit.jupiter.api.Test
    fun isUniqueTest(){
        fun test(s: String, value: Boolean, msg: String){
            kotlin.test.assertEquals(isUnique_mySol(s), value, "$msg mySol")
        }
        test("hello", false, "Failed hello")
        test("trying", true, "Failed trying")
    }

    @org.junit.jupiter.api.Test
    fun checkPermutationTest(){
        fun test(a: String, b:String, value: Boolean, msg: String){
            kotlin.test.assertEquals(checkPermutation_mySol(a, b), value, "$msg mySol")
            kotlin.test.assertEquals(checkPermutation_bookSol(a, b), value, "$msg bookSol")
        }
        test("hello", "oehll", true, "Failed hello")
        test("abcde", "aaaaaaaaaaa", false,"Failed different length")
        test("abcde", "aaaaa", false,"Failed not permutation but same length")
        test("abcde", "cedaa", false,"Failed abcde, cedaa")
    }
}