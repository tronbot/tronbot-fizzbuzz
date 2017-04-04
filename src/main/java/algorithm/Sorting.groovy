package algorithm

import java.nio.file.attribute.AclEntry

/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 27, 2017
 */


public static <T extends Comparable> T[] bubbleSort(T[] array, boolean descending = false){
	for(int end = array.length - 1; end >1 ; end --){
		for(int start = 0 ; start < end ; start++){
			if((!descending && array[start].compareTo(array[start+1])>0)
			|| (descending && array[start].compareTo(array[start+1])<0)){
				swapValues(array, start, start+1)
			}
		}
	}
	return array
}

public static <T extends Comparable> T[] selectSort(T[] array, boolean descending = false){
	for(int curr = 0 ; curr < array.length ; curr++){
		for(int next = curr+1 ; next < array.length ; next++){
			if((!descending && array[curr].compareTo(array[next])>0)
			|| (descending && array[curr].compareTo(array[next])<0)){
				swapValues(array, curr, next)
			}
		}
	}
	return array
}


public static <T extends Comparable> T[] insertionSort(T[] array){
	for(int i = 1;i < array.length;i++){
		T insert = array[i]
		int left = i
		while(left > 0 && array[left - 1]>insert){
			array[left] = array[left-1]
			left--
		}
		array[left] = insert
		println "debug:\t${array}"
	}
	return array
}

public static <T> Integer[] binarySearch(T[] array, T value){
	List<Integer> occurrances = new ArrayList<>()
	int lowIdx = 0
	int highIdx = array.length -1
	while(lowIdx <= highIdx){
		int midIdx = (lowIdx+highIdx)/2
		if(array[midIdx].compareTo(value)<0){
			lowIdx = midIdx + 1
		}else if(array[midIdx].compareTo(value)>0){
			highIdx = midIdx - 1
		}else{
			int left = midIdx
			int right = midIdx
			while(array[--left].compareTo(value) == 0){
				occurrances.add(left)
			}
			occurrances.add(midIdx)
			while(array[++right].compareTo(value) == 0){
				occurrances.add(right)
			}
			lowIdx = highIdx+1
		}
	}
	return occurrances as Integer[]
}


private static <T> void swapValues(T[] array, int x, int y){
	T temp = array[x]
	array[x] = array[y]
	array[y] = temp
}

private static Integer[] getRandomArray(int scale, int size){
	Integer[] array = new Integer[size]
	(0..size-1).each{
		array[it] = (Math.random() * scale) as Integer
	}
	return array
}

Integer[] input = getRandomArray(100, 10)
println "Input:\t${input}"
//Integer[] ascended = bubbleSort(input)
//Integer[] ascended = selectSort(input)
Integer[] ascended = insertionSort(input)
println "Ascd:\t${ascended}"
Integer target = 60
Integer[] occurrances = binarySearch(ascended, target)
println "Found ${target} at ${occurrances}"



