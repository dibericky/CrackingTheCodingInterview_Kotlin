fun main(args: Array<String>){
    val arrA = intArrayOf(3,1,2,4,5)
    val arrB = intArrayOf(2,1,1,3,5)
//    printUnorderedPairs(arrA, arrB)
    isPrimeTest()
//    fibTest()
//    allFibWithCache(6)

//    permutation("ciao")
 //   powersOf2Test()
}

/**
 *  a = size(arrayA)
 *  b = size(arrayB)
 * Complexity:   O(a*b)
 * it's not O(N^2) because there are two different inputs. Both matters.
 */
fun printUnorderedPairs(arrayA: IntArray, arrayB: IntArray){
    for(i in 0 until arrayA.size){ // size(arrayA)
        for(j in 0 until arrayB.size){  // size(arrayB)
            if(arrayA[i] < arrayB[j]) // O(1)
                println("$i $j")
        }
    }
}

/**
 * Complexity: O(N), N=#node
 * What It Means
 *      This code touches each node in the tree once and does a constant
 *      time amount of work with each "touch".
 *      Therefore, the runtime will belinear in terms of number of nodes.
 *      If there are N nodes, then the runtime is O(N)
 * Recursive Pattern
 *      The runtime of a recursive function with multiple branches is tipically
 *      O(branches^depth).
 *      There are 2 branches at each call, so we're looking at O(2^depth)
 *      The tree is a balanced binary search tree. Therefore, if there are
 *      N total nodes, then depth is roughly logN.
 *      So: O(2^logN) = O(N)
 *
 */
fun sum(node: Node?) : Int{
    /**
     * Node is the root of a balanced search tree
     */
    if(node == null)return 0
    return sum(node.left) + node.value + sum(node.right)
}

/**
 * Complexity: O(sqrt(N))
 *
 * The for loop will start when x = 2, it stops when x = sqrt(n)
 */
fun isPrime(n: Int) : Boolean{
    fun Int.sqrt() = Math.sqrt(toDouble()).toInt()

    for(x in 2..(n.sqrt())){
        if(n%x == 0)return false
    }
    return true
}

/**
 * Complexity: O(N)
 *
 * This is just a straight recursion from n down to 1.
 */
fun factorial(n: Int) : Int{
    if(n<0) return -1
    if(n == 0) return 1

    return n*factorial(n-1)
}


/**
 * Complexity: O(n^2 * n!)
 *
 * permutation is called n! times
 * every "leaf" has a path with length n.
 * Therefore, we know there will be no more that n*n! nodes (function calls) in this tree.
 *
 * How long does each function call take?
 *      Executing println takes O(n) time since each character needs to be printed.
 *          val rem = '...'
 *          permutation(.., ..)
 *      also take O(n) time combines, due to the string concatenation.
 *      Each node in out call tree therefore corresponds to O(n) work.
 * In conclusion:
 *      Since we are calling permutation O(n*n!) times and each one takes O(n) time
 *      -> the total runtimes will not exceed O(n^2 * n!)
 */
fun permutation(str: String, prefix: String=""){
    println("\t\t $str   $prefix")
    if(str.isEmpty()) println(prefix)
    else{
        for(i in 0 until str.length){
            val rem :String = "${str.substring(0, i)}${str.substring(i+1)}"
            permutation(rem, prefix+str[i])
        }
    }
}

/**
 * Complexity: O(2^N)
 * There are 2 branches per call, and we go as deep as N, therefore the runtime is O(2^N)
 *
 */
fun fib(n: Int) : Int{
    if(n <2) return n
    return fib(n-1) + fib(n-2)
}

/**
 * Complexity: O(2^N)
 *
 * Why is not O(N*2^N) ?
 *      The block inside the for is O(2^i), not O(2^N), so its weight depends on i.
 *      The block is executed N times, so the weigth is: 2^0 + 2^1 + .. + 2^N.
 *      So the complexity is: O(2^N)
 */
fun allFib(n:Int){
    for(i in 0 until n){ // n times
        println("$i : ${fib(i)}")  // O(2^i)
    }
}

/**
 * Complexity: O(N)
 *
 * At each call to fib(i) we have already computed and stored the value of
 * fib(i-1) and fib(i-2).
 * We just lookup those value, sum them, store the new result, and return.
 * This takes a constant amount of time.
 *
 * This technique, called memorization, is a very common one to optimize exponential
 * time recursive algorithms.
 */
fun allFibWithCache(n: Int){
    val memo = IntArray(n+1)
    for(i in 0..n){
        println("$i : ${fibWithCache(i, memo)}")
    }
}
fun fibWithCache(n: Int, memo: IntArray):Int{
    if(n<2)return n
    if(memo[n]>0)return memo[n]
    memo[n] = fibWithCache(n-1, memo)+fibWithCache(n-2, memo)
    return memo[n]
}

/**
 * Complexity: O(logN)
 *
 * The runtime is the number of times we can divide 'n' by 2 until we get down to the base case (1).
 * The number of times we can halve N until we get 1 is O(logN).
 *
 * Rate of Increase
 *      Another way to approach the runtime is to think about how the runtime changes as n gets bigger.
 *      After all, this is exactly what big O means.
 *      If N goes form P to P+1, the number of calls to powersOfTwo might not change at all.
 *      When will the number of calls to powersOfTwo increase? It wil increase by 1 each time N double in size.
 *
 *      So, each time n doubles, the number of calls to powersOfTwo increases by 1. Therefore, the number of calls
 *      to powersOfTwo is the number of timees you can double 1 until you get n. It is x in the equation 2^x = n.
 *      So the runtime is O(logN)
 */
fun powersOf2(n: Int):Int{
    if(n<1)return 0

    return when(n){
        1 -> 1
        else ->{
            val prev = powersOf2(n/2) //integer part of n/2.
            val curr = prev*2
            println(curr)
            return curr
        }
    }
}

fun powersOf2Test(){
    assert(powersOf2(-1) == 0)
    assert(powersOf2(1) == 1)
    assert(powersOf2(50) == 32)
}
fun fibTest(){
    assert(fib(0) == 0)
    assert(fib(1) == 1)
    assert(fib(2) == 1)
    assert(fib(5) == 5)
    assert(fib(6) == 8)
}
fun isPrimeTest(){
    assert(!isPrime(4))
    assert(isPrime(23))
    assert(!isPrime(33))
}
class Node(val left: Node, val right: Node, val value: Int)