package io.tronbot.fizzbuzz.v7;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 
 * 7. Joint Membership
 * 
 * Given two long lists of integers, A and B, use whatever tool/program you like
 * to list out:
 * 
 * - All elements present in both lists (elements must be present in list A and
 * list B)
 * 
 * - All elements present in exactly one list, but not the other (“in A but not
 * in B” as well as “in B but not in A”)
 * 
 * - All elements present in any list, but discarding duplicates
 * 
 * @Author Juanyong Zhang
 * @Date Oct 22, 2016
 */
public class JointMembership {

	public static <T> List<T> getMutualElements(List<T> listA, List<T> listB) {
		if (null == listA || null == listB) {
			return null;
		}
		return listA.stream().filter(listB::contains).collect(Collectors.toList());
	}

	public static <T> List<T> getExclusiveList(List<T> listA, List<T> listB) {
		if (null == listA) {
			return null;
		}
		if (null == listB) {
			return listA;
		}
		return listA.stream().filter(((Predicate<T>) (listB::contains)).negate()).collect(Collectors.toList());
	}

	public static <T> List<T> unionLists(List<T> listA, List<T> listB) {
		if (null == listA) {
			return listB;
		}
		if (null == listB) {
			return listA;
		}
		return Stream.concat(listA.stream(), listB.stream()).distinct().collect(Collectors.toList());
	}
}
