package intro

/**
 * Problem: Print all positive integer solutions to the equation a^3 + b^3 = c^3 = d^3,
 * where a,b,c,d are integers between 1 and 1000
 */
class Problem1 {
    /**
     * Complexity: O(N^4)
     * Iterate through all possible values of a,b,c,d and checks if that combination happens to work.
     *Problem of this version:
     * It's unnecessary to continue checking for the other possible values of d, because only
     * one could work. We should at least break after we find a valid solution
     */
    fun unnecessaryWork_Problem1() {
        val n = 1000
        for (a in 1..n) {
            for (b in 1..n) {
                for (c in 1..n) {
                    for (d in 1..n) {
                        if (a.pow(3) + b.pow(3) == c.pow(3) + d.pow(3)) {
                            println("$a, $b, $c, $d")
                        }
                    }
                }
            }
        }
    }

    /**
     * It's still O(N^4).
     * It's still a good, quick fix to make.
     * Problem of this version:
     * If there's only one d value for each (a,b,c), then we can just compute it.
     */
    fun unnecessaryWork2() {
        val n = 1000
        for (a in 1..n) {
            for (b in 1..n) {
                for (c in 1..n) {
                    for (d in 1..n) {
                        if (a.pow(3) + b.pow(3) == c.pow(3) + d.pow(3)) {
                            println("$a, $b, $c, $d")
                            break
                        }
                    }
                }
            }
        }
    }

    /**
     * Complexity: O(N^3)
     *
     * if(d in 0..n checks if the value is valid, because d must be in (0, 1000) range.
     *
     *Problem of this version:
     * Why do we keep on computing all (c,d) pairs for each (a,b) pairs?
     * (c,d) will assume the same value of (a,b).
     * So we should just create the list of (c,d) pairs once. Then, when we have an (a,b) pair, find
     * the matches within the (c,d) list.
     */
    fun unnecessaryWork3() {
        val n = 1000
        for (a in 1..n) {
            for (b in 1..n) {
                for (c in 1..n) {
                    val d: Int = cubeRootOfSumOf(a.pow(3), b.pow(3), c.pow(3))
                    if (d in 0..n && a.pow(3) + b.pow(3) == c.pow(3) + d.pow(3)) {
                        println("$a, $b, $c, $d")
                        break
                    }

                }
            }
        }
    }

    /**
     *Complexity: O(N^2)
     *
     * Problem of this version:
     * Actually, once we have the map of all (c,d) pairs, we can just use that directly.
     * We don't need to generate the (a,b) pairs. Each (a,b) will already be in the map.
     */
    fun duplicateWork() {
        val n = 1000
        val map: MutableMap<Int, MutableList<Pair<Int, Int>>> = mutableMapOf()
        for (c in 1..n) {
            for (d in 1..n) {
                val result = c.pow(3) + d.pow(3)
                map.getValueAndAppendToList(Pair(c, d), result)
            }
        }

        for (a in 1..n) {
            for (b in 1..n) {
                val result = a.pow(3) + b.pow(3)
                val list = map.get(result)
                list?.forEach { println("($a, $b) : (${it.first}, ${it.second}") }
            }
        }
    }

    /**
     * Complexity: O(N^2)
     */
    fun finalVersion(n: Int = 1000) {
        val map: MutableMap<Int, MutableList<Pair<Int, Int>>> = mutableMapOf()
        for (c in 1..n) {
            for (d in 1..n) {
                val result = c.pow(3) + d.pow(3)
                map.getValueAndAppendToList(Pair(c, d), result)
            }
        }

        map.forEach { result, list ->
            print("Result: ")
            list.forEach {
                print("(${it.first}, ${it.second}) ")
            }
            println()
        }
    }


    fun <T> MutableMap<Int, MutableList<T>>.getValueAndAppendToList(element: T, value: Int): MutableList<T> {
        val list = getOrPut(value) { mutableListOf() }
        list.add(element)
        return list
    }


    fun cubeRootOfSumOf(a: Int, b: Int, c: Int) = Math.cbrt((a + b + c).toDouble()).toInt()


    fun Int.pow(exponent: Int): Int = Math.pow(toDouble(), exponent.toDouble()).toInt()

}

/**
 * Given a smaller string s and a bigger string b, design an algorithm to find all permutations
 * of the shorter string within the longer one.
 * Print the location of each intro.permutation.
 */
class Problem2 {

        fun mySolution(longer: String, shorter: String) {
            var min = 0
            val windowLength = shorter.length
            val map: MutableMap<Char, Int> = mutableMapOf()
            val mapSupp: MutableMap<Char, Int> = mutableMapOf()
            map.initWithCharsOf(shorter)

            for (i in 0..(longer.length - windowLength)) {
                val window = longer.substring(i, i + windowLength)
                mapSupp.clear()
                mapSupp.initWithCharsOf(window)
                if (mapSupp.keys.size == map.keys.size) { //if size is different than I'm sure it's not a intro.permutation
                    if (map.all { mapSupp[it.key] == it.value }) { //if is equal for all element of the map
                        println("[$i, ${i + windowLength - 1}] = $window")
                    }
                }
            }
        }

        fun MutableMap<Char, Int>.initWithCharsOf(s: String) {
            s.forEach {
                val num = getOrPut(it) { 0 }
                this[it] = num + 1
            }
        }
}

class BCR {

    /**
     * Question: Given two sorted arrays, find the number of elements in common. The  arrays are the same length and each
     * has all distinct elements.
     * example:
     *      A: 13   27  (35)  (40)  49  (55)  59
     *      B: 17   (35)  39  (40)  (55)  58  60
     *
     * Bruteforce algorithm: O(N^2)
     * intro.BCR (Best Conceivable Runtime): O(N). We know we will hae to look at each element at least once and there are
     * 2N total elements.
     * We are driving towards an O(N) algorithm or an O(N logN) algorithm, because if we imagine out current
     * algorithm's runtime ( O(N^2) ) as O(N*N), then getting to O(N) or O(N logN) might mean reducing the second
     * O(N) in the equation to O(1) or O(logN).
     * This is one way that intro.BCR can be useful. We can use the runtime to get a "hint" for what we need to reduce.
     * That second O(N) comes from searching. The array is sorted. Can we search in a sorted array in faster than
     * O(N) time? Yes! Binary search. O(logN). So we would have O(N logN).
     * Brute Force:  O(N^2)
     * Improved Algorithm: O(N logN)
     * Optimal Algorithm: ?
     * intro.BCR: O(N)
     *
     * We could just throw everything in B into a hash table. This will take O(N) time. Then, we just go through A and look up
     * each element in the hashtable. This look up is O(1), so our runtime will be O(N).
     * Can we do better? No, not in terms of runtime. We have achieved the fastest possible runtime.
     * We could improve space complexity, in fact our algorithm would have the exact same runtime if the data wasn't sorted.
     * So why did the interview give us sorted array?
     *
     * We're looking for an algorithm that operates in O(1) space (probably). We already have an O(N) space algorithm with optimal runtime.
     * So we need to drop the hash table. We should use the fact that the arrays are sorted.
     * We could just do a linear search. As long as the linear search in B is just picking up where the last one left off, we know
     * that we're going to be operating in linear time.
     */

    companion object {
        /**
         * Iterative version
         */
        fun numElementInCommonIterative(arrA: IntArray, arrB: IntArray): Int {
            var startIndex = 0
            var found = 0

            for (indexB in 0 until arrB.size) {
                for (indexA in startIndex until arrA.size) {
                    if (arrB[indexB] == arrA[indexA]) {
                        found++
                        startIndex = indexA + 1
                        break
                    } else if (arrB[indexB] < arrA[indexA]) {
                        break
                    }
                }
            }

            return found
        }

        /**
         * Recursive version
         */
        fun numElementCommonRecV1(arrA: IntArray, arrB: IntArray, indexA: Int = 0, indexB: Int = 0): Int {
            if (indexA >= arrA.size || indexB >= arrB.size) return 0

            for (index in indexA until arrA.size) {
                if (arrB[indexB] == arrA[index]) return 1 + numElementCommonRecV1(arrA, arrB, index + 1, indexB + 1)
                else if (arrB[indexB] < arrA[index]) return numElementCommonRecV1(arrA, arrB, index, indexB + 1)
            }
            return 0
        }

        /**
         * Tail Recursion version
         */
        fun numElementCommonRecV2(arrA: IntArray, arrB: IntArray): Int {
            data class Response(var result: Int)

            tailrec fun recursion(arrA: IntArray, arrB: IntArray, indexA: Int = 0, indexB: Int = 0, response: Response = Response(0)): Response {
                if (indexA >= arrA.size || indexB >= arrB.size) return response

                for (index in indexA until arrA.size) {
                    if (arrB[indexB] == arrA[index]) {
                        response.result++
                        return recursion(arrA, arrB, index + 1, indexB + 1, response)
                    } else if (arrB[indexB] < arrA[index]) return recursion(arrA, arrB, index, indexB + 1, response)
                }
                return response
            }

            return recursion(arrA, arrB).result

        }
    }

}