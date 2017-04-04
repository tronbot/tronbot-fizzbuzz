package hackerrank

import java.util.PriorityQueue
import java.util.Comparator

/**
 * https://www.hackerrank.com/challenges/find-the-running-median?h_r=internal-search
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 29, 2017
 */

public class Solution{

	public static List<Double> getMedians(List<Integer> numbers){
		final Comparator<Integer> reverseComparator = new Comparator<Integer>(){
					int compare(Integer o1, Integer o2){
						return -1 * o1.compareTo(o2)
					}
				}
		PriorityQueue<Integer> lowers = new PriorityQueue(reverseComparator)
		PriorityQueue<Integer> highers = new PriorityQueue()
		List<Double> medians = new ArrayList()
		for(Integer num : numbers){
			addNumber(num, lowers, highers)
			rebalance(lowers, highers)
			medians.add(getMedian(lowers, highers))
		}
		return medians
	}

	private static void addNumber(Integer num, PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers){
		if(lowers.size ==0 || num < lowers.peek()){
			lowers.add(num)
		}else{
			highers.add(num)
		}
	}

	private static void rebalance(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers){
		PriorityQueue<Integer> biggerHeap = lowers.size() > highers.size() ? lowers : highers
		PriorityQueue<Integer> smallerHeap = lowers.size() > highers.size() ? highers : lowers

		if(biggerHeap.size() - smallerHeap.size() > 1){
			smallerHeap.add(biggerHeap.poll())
		}
	}

	private static Double getMedian(PriorityQueue<Integer> lowers, PriorityQueue<Integer> highers){
		PriorityQueue<Integer> biggerHeap = lowers.size() > highers.size() ? lowers : highers
		PriorityQueue<Integer> smallerHeap = lowers.size() > highers.size() ? highers : lowers
		if(biggerHeap.size() == smallerHeap.size()){
			return ((Double)biggerHeap.peek() +  smallerHeap.peek())/2
		}else{
			return biggerHeap.peek()
		}
	}
}



String inputString = '''10
1
2
3
4
5
6
7
8
9
10
'''

InputStream is = new ByteArrayInputStream(inputString.getBytes())
//is = System.in
Scanner input = new Scanner(is)
List<Integer> numbers = new ArrayList()
int lineNum = 0
int lineCount = 1
while (lineCount>=lineNum) {
	String ln = input.nextLine()
	if(lineNum == 0){
		lineCount = ln as Integer
	}else{
		numbers.add(ln as Integer)
	}
	lineNum ++
}
List<Double> medians = Solution.getMedians(numbers)
for(Double median:medians){
	println median
}