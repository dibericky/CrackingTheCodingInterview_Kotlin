fun main(args: Array<String>) {
   // Problem1().finalVersion(30)
   // Problem2().initWithCharsOfTest()

    Problem2().mySolution("cbabadcbbabbcbabaabccbabc","abbc")
}

class Problem1 {
/*
Problem: Print all positive integer solutions to the equation a^3 + b^3 = c^3 = d^3,
        where a,b,c,d are integers between 1 and 1000
 */

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

class Problem2{
    /*
    Given a smaller string s and a bigger string b, design an algorithm to find all permutations
    of the shorter string within the longer one.
    Print the location of each permutation.

     */

    fun mySolution(longer: String, shorter: String){
        var min = 0
        val windowLength = shorter.length
        val map : MutableMap<Char, Int> = mutableMapOf()
        val mapSupp : MutableMap<Char, Int> = mutableMapOf()
        map.initWithCharsOf(shorter)

        for(i in 0..(longer.length-windowLength)){
            val window = longer.substring(i, i+windowLength)
            mapSupp.clear()
            mapSupp.initWithCharsOf(window)
            if(mapSupp.keys.size == map.keys.size){ //if size is different than I'm sure it's not a permutation
                if(map.all { mapSupp[it.key] == it.value }){ //if is equal for all element of the map
                    println("[$i, ${i+windowLength-1}] = $window")
                }
            }
        }
    }

    fun MutableMap<Char, Int>.initWithCharsOf(s: String){
        s.forEach {
            val num = getOrPut(it){0}
            this[it] = num+1
        }
    }

    fun initWithCharsOfTest(){
        val map : MutableMap<Char, Int> = mutableMapOf()
        val prova = "abbc"
        map.initWithCharsOf(prova)
        assert(map['a'] == 1)
        assert(map['b'] == 2)
        assert(map['c'] == 1)
    }
}
