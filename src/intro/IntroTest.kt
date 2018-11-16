package intro

import intro.Intro.Companion.fib
import intro.Intro.Companion.isPrime
import intro.Intro.Companion.powersOf2
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class IntroTest{
    @Test
    fun powersOf2Test(){
        assert(powersOf2(-1) == 0)
        assert(powersOf2(1) == 1)
        assert(powersOf2(50) == 32)
    }
    @Test
    fun fibTest(){
        assert(fib(0) == 0)
        assert(fib(1) == 1)
        assert(fib(2) == 1)
        assert(fib(5) == 5)
        assert(fib(6) == 8)
    }
    @Test
    fun isPrimeTest(){
        assert(!isPrime(4))
        assert(isPrime(23))
        assert(!isPrime(33))
    }
}