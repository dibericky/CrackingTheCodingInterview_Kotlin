package intro

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Problem2Test{
    @Test
    fun initWithCharsOfTest() {
        Problem2().apply { //ToDo: really bad solution...
            val map: MutableMap<Char, Int> = mutableMapOf()
            val prova = "abbc"
            map.initWithCharsOf(prova)
            assert(map['a'] == 1)
            assert(map['b'] == 2)
            assert(map['c'] == 1)
        }
    }
}