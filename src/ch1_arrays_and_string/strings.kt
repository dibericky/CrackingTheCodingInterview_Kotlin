package ch1_arrays_and_string

import java.lang.StringBuilder
import java.util.*
import kotlin.test.assertEquals

/**
 * Concatenate N string
 */
fun main(args: Array<String>){
   isUniqueTest()
   checkPermutationTest()
}

/**
 * Complexity: O(N^2)
 *
 * On each concatenation, a new copy of the string is created, and the two stringa
 * are copied over, character by character.
 * The first iteration requires us to copy x characters.
 * The second iteration requires copying 2x characters, and so on.
 * The total time therefore is O(x + 2x + ... nx).
 * 1 + 2 + 3 + .. + n = n*(n-1)/2 = (n^2 - n )/2 ->  O(N^2)
 */
fun joinWords_bad(words: Array<String>) : String{
    var sentence = ""
    for(w in words){
        sentence += w
    }
    return sentence
}

/**
 * String builder simply craetes a resizable array of all strings, copying them back
 * to a string only when necessary.
 */
fun joinWords_good(words: Array<String>) : String{
    val sentence = StringBuilder()

    for(w in words){
        sentence.append(w)
    }
    return sentence.toString()
}

/*
*    Interview Questions
*/

/**
 * Implement an algorithm to determine if a string has all unique characters.
 * What if you cannot use additional data structures?
 */
fun isUnique_mySol(s: String) : Boolean{
    s.forEach {
        if(s.replace(it.toString(), "").length != s.length-1)return false
    }
    return true
}
/*
Alternative:
    1) Compare every character of the string to every other character of the string. -> O(N^2) time and O(1) space.
    2) We could sort the string (maybe a copy) in O(N logN) time and then linearly check the string for neighboring
        that are identical. but many sorting algorithms take up extra space.
 */

/**
 * Given two strings, write a method to decide if one is a permutation of the other
 */
fun checkPermutation_mySol(a: String, b: String) : Boolean{
    if(a.length != b.length)return false

    val map : MutableMap<Char, Int> = mutableMapOf()

    a.forEach {
        val num = map.getOrPut(it){0}
        map[it] = num+1
    }
    b.forEach {
        val num = map.getOrPut(it){0}
        if(num == 0)return false
        map[it] = num-1
    }
    return true
}

fun checkPermutation_bookSol(a:String, b:String):Boolean{
    fun sort(s:String):String{
        val chars = s.toCharArray()
        Arrays.sort(chars)
       return StringBuilder().append(chars).toString()
    }
    return sort(a) == sort(b)
}

fun isUniqueTest(){
    fun test(s: String, value: Boolean, msg: String){
        assertEquals(isUnique_mySol(s), value, "$msg mySol")
    }
    test("hello", false, "Failed hello")
    test("trying", true, "Failed trying")
}

fun checkPermutationTest(){
    fun test(a: String, b:String, value: Boolean, msg: String){
        assertEquals(checkPermutation_mySol(a,b), value, "$msg mySol")
        assertEquals(checkPermutation_bookSol(a,b), value, "$msg bookSol")
    }
    test("hello", "oehll", true, "Failed hello")
    test("abcde", "aaaaaaaaaaa", false,"Failed different length")
    test("abcde", "aaaaa", false,"Failed not permutation but same length")
    test("abcde", "cedaa", false,"Failed abcde, cedaa")
}