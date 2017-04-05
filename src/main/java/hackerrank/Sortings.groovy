package hackerrank
/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Apr 4, 2017
 */
class QuickSort{
	@SafeVarargs
	public static <T extends Comparable> void sort(T[] array){
		quickSort(array, 0, array.length-1)
	}

	private static <T extends Comparable> void quickSort(T[] array, int leftIdx, int rightIdx){
		if(leftIdx>=rightIdx){
			return
		}
		T pivot = array[(leftIdx+rightIdx)/2]
		int index = partition(array, leftIdx, rightIdx, pivot)
		quickSort(array, leftIdx, index-1)
		quickSort(array, index, rightIdx)
	}

	private static <T extends Comparable> int partition(T[] array, int leftIdx, int rightIdx, T pivot){
		while(leftIdx<=rightIdx){
			while(array[leftIdx].compareTo(pivot)<0){
				leftIdx++
			}
			while(array[rightIdx].compareTo(pivot)>0){
				rightIdx--
			}
			if(leftIdx<=rightIdx){
				println "swap:  ${pivot} - ${array[leftIdx]}@${leftIdx} : ${array[rightIdx]}@${rightIdx}"
				swap(array, leftIdx, rightIdx)
				println "${array}"
				leftIdx++
				rightIdx--
			}
		}
		return leftIdx
	}
	
	private static void swap(Object[] array, int leftIdx, int rightIdx){
		Object temp = array[leftIdx]
		array[leftIdx] = array[rightIdx]  
		array[rightIdx] = temp
	}
}


Integer[] array = new Integer[20]
(0..array.length-1).each{
	array[it] = Math.random()*100 as Integer
}
println array
//QuickSort.sort(array)
Arrays.parallelSort(array)
println array

