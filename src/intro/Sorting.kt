package intro

class QuickSort{
    companion object {
        fun quickSort(arr: IntArray, min: Int=0, max: Int=(arr.size-1)){
            if(arr.isEmpty() || min == max)return

            val pivot = arr[max]
            var wall = min

            for(curr in min until max){
                if(arr[curr] < pivot){
                    swap(arr, curr, wall)
                    wall++
                }
            }
            swap(arr, wall, max) //max is the index of pivot

            if(wall>0)
                quickSort(arr, min, wall - 1)
            if(wall<max)
                quickSort(arr, wall + 1, max)
        }

        private fun swap(arr: IntArray, first: Int, second: Int){
            if(first == second)return

            val supp = arr[first]
            arr[first] = arr[second]
            arr[second] = supp
        }
    }
}

