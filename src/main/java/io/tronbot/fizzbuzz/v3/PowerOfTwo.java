package io.tronbot.fizzbuzz.v3;

/**
 * Implement a method / function that takes an int n as input and returns a
 * boolean, whose value is true, if and only if n is a power of 2. Pseudocode is
 * sufficient. Implement a second algorithm for the same problem.
 * 
 * @Author Juanyong Zhang
 * @Date Oct 24, 2016
 */
public class PowerOfTwo {
	public static boolean check(long num) {
		return (num > 0) && ((num & (num - 1)) == 0);
	}
}
