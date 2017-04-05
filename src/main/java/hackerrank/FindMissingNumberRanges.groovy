package hackerrank





//I: [0, 1, 37, 39, 75]
//O: "2-36,38,40-74,76-99"
//int[] input = [4, 37, 39, 75]
int[] input = [0, 1, 37, 39, 75]
int min = 0
int max = 99
println "Find missing numbers of ${input} from ${min} to ${max}"
String missingNumbers = getMissingNumbers(input, min, max)
println missingNumbers

public String getMissingNumbers(int[] array, int min, int max){
	if(!array){
		return "${min}-${max}"
	}
	String missingNumbers = ''
	List<Integer> edgeNums = [min]
	for(int idx = 0 ; idx < array.length; idx++){
		int num = array[idx]
		if(num<=max&&num>=min){
			edgeNums.add(num-1)
			edgeNums.add(num+1)
		}
	}
	edgeNums.add(max)
	println "Edge Numbers: ${edgeNums}"
	return "MissingNumbers: ${printEdgeNums(edgeNums as int[])}"
}


public String printEdgeNums(int[] nums){
	String numStr = ''
	for(int i =0 ; i < nums.length-1; i=i+2){
		Integer curr = nums[i]
		Integer next = nums[i+1]

		if(curr<next){
			numStr += "${curr}-${next},"
		}else if(curr==next){
			numStr += "${curr},"
		}
	}
	return numStr?numStr.substring(0, numStr.length()-1):''
}

