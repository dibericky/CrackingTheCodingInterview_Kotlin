package ch6_math_logic_puzzle

class Primes{
    companion object {
        fun isPrimeNaive(n: Int):Boolean{
            if(n<2)return false
            for(i in 2 until n){
                if(n%2==0)return false
            }
            return true
        }

        /**
         * If a number N is not prime, than exist a number M < sqrt(N) so that N%M == 0
         */
        fun isPrimeSlightlyBetter(n: Int):Boolean{
            if(n<2)return false
            val sqrt = Math.sqrt(n.toDouble()).toInt()
            for(i in 2 until sqrt){
                if(n%2==0)return false
            }
            return true
        }

        /**
         * Generating a list of Primes
         */
        fun sieveOfEratosthenes(max: Int) : BooleanArray{
            fun crossOff(flags: BooleanArray, prime: Int){
                var i = prime*prime //because if we have a k*prime, where k<prime, this value would have already been crossed off in a prior iteration
                while(i<flags.size){
                    flags[i] = false
                    i += prime
                }
            }
            fun getNextPrime(flags: BooleanArray, prime: Int) = flags.mapIndexed { index, value -> Pair(index, value) }
                    .firstOrNull { it.first > prime && it.second }?.first

            val flags = BooleanArray(max+1){it>=2} //set all flags to true other than 0 and 1

            var prime = 2
            val sqrt = Math.sqrt(max.toDouble()).toInt()

            while(prime <= sqrt){
                //Cross off remaining multiples of prime
                crossOff(flags, prime)

                //find next value which is true
                prime = getNextPrime(flags, prime)?: flags.size

            }
            return flags
        }

        fun isPrimeClever(n: Int) : Boolean{
            val primes = sieveOfEratosthenes(n)
            return primes[n]
        }
    }
}