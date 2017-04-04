package io.tronbot.fizzbuzz.v6;

import java.math.BigDecimal;

/**
 * 6. Adding Two Numbers
 * 
 * Write a Java program to add two numbers.
 * 
 * Anticipate edge-case problems and future requirements for this program, and
 * explain how you will adapt.
 * 
 * @Author Juanyong Zhang
 * @Date Oct 23, 2016
 */
public class AddTwoNumber {
	public static BigDecimal add(String a, String b) {
		if (null == a || null == b) {
			throw new IllegalArgumentException("The input value can't be null!");
		}
		BigDecimal aNum = new BigDecimal(a.replaceAll(",", ""));
		BigDecimal bNum = new BigDecimal(b.replaceAll(",", ""));
		return aNum.add(bNum);
	}
}
