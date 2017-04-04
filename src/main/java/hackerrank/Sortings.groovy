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
		if(leftIdx>rightIdx){
			return
		}
		T pivot = array[(leftIdx+rightIdx)/2]
		int index = partition(array, leftIdx, rightIdx, pivot)
		quickSort(array, 0, leftIdx/2-1)
		quickSort(array, rightIdx, array.length)
	}

	private static <T extends Comparable> void partition(T[] array, int leftIdx, int rightIdx, T pivot){
		while(leftIdx<=rightIdx){
			T left = array[leftIdx]
			T right = array[rightIdx]
			
			
			
		}
	}
}
