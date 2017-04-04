package hackerrank
/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 30, 2017
 */

int getLonelyNums(int[] nums){
	int result = 0
	nums.each { num ->
		println "${result}^${num}=${result^num}"
		result ^= num
	}
	return result
}

List<Integer> nums = new ArrayList()
(0..10).each {
	nums.add(Math.random()*10 as Integer)
}
println nums
println getLonelyNums(nums.toArray(new int[nums.size()]))