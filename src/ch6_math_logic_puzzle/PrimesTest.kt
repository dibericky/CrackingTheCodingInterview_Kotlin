package ch6_math_logic_puzzle

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class PrimesTest{

    @Test
    fun listOfPrimes(){
        fun check(max: Int, primeList: List<Int>, expected: Boolean){
            val primes = Primes.sieveOfEratosthenes(max)
            assertEquals(expected, primes.mapIndexed { index, value -> Pair(index, value)}
                    .all { if(primeList.contains(it.first))it.second else !it.second })
        }
        check(10, listOf(2,3,5,7), true)
        check(10, listOf(2,4,5,7), false)
    }

    @Test
    fun isPrime(){
        fun check(n: Int, expected: Boolean){
            assertEquals(expected, Primes.isPrimeClever(n))
            assertEquals(expected, Primes.isPrimeSlightlyBetter(n))
        }
        check(10, false)
        check(5, true)
    }


}