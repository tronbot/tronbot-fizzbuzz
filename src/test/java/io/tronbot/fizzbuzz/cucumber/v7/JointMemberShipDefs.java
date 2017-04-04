package io.tronbot.fizzbuzz.cucumber.v7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Assert;

import cucumber.api.Scenario;
import cucumber.api.java8.En;
import io.tronbot.fizzbuzz.v7.JointMembership;

/**
 * 
 * @Author Juanyong Zhang
 * @Date Oct 23, 2016
 */
public class JointMemberShipDefs implements En {
	private Scenario scenario;

	private final List<Integer> LONG_LIST;
	private final int LONG_LIST_SIZE = 1000;

	private List<Integer> listA;
	private List<Integer> listB;
	private List<Integer> mutualList;
	private List<Integer> listAExclusive;
	private List<Integer> unionList;

	@cucumber.api.java.Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
	}

	public JointMemberShipDefs() {
		LONG_LIST = new ArrayList<>();
		for (int i = 0; i < LONG_LIST_SIZE; i++) {
			LONG_LIST.add(i);
		}
		Given("^List A is array of divisible int by (\\d+)$", (Integer d) -> {
			listA = LONG_LIST.stream().filter(val -> val % d == 0).collect(Collectors.toList());
			printout("ListA", listA);
		});
		Given("^List B is array of divisible int by (\\d+)$", (Integer d) -> {
			listB = LONG_LIST.stream().filter(val -> val % d == 0).collect(Collectors.toList());
			printout("listB", listB);
		});

		When("^find common elements as new list$", () -> {
			mutualList = JointMembership.getMutualElements(listA, listB);
		});

		Then("^all new list elements is divisible by (\\d+) and (\\d+)$", (Integer d1, Integer d2) -> {
			Assert.assertTrue("Result List is not empty.", mutualList != null && !mutualList.isEmpty());
			Assert.assertFalse(String.format("All result list element is divisible by %s and %s", d1, d2),
					mutualList.stream().filter(val -> ((val % d1) + (val % d2)) != 0).findAny().isPresent());
			printout("Mutual Element List", mutualList);
		});

		When("^find listA exclusive elements as new list$", () -> {
			listAExclusive = JointMembership.getExclusiveList(listB, listA);
		});

		Then("^all new list elements is divisible by (\\d+) but not (\\d+)$", (Integer d1, Integer d2) -> {
			Assert.assertTrue("Result List is not empty.", listAExclusive != null && !listAExclusive.isEmpty());
			Assert.assertFalse(String.format("All result list element is divisible by %s but not %s", d1, d2),
					listAExclusive.stream().filter(val -> val % d1 != 0 && val % d2 == 0).findAny().isPresent());
			printout("ListA Exclusive List", listAExclusive);
		});

		When("^union listA and listB as new list$", () -> {
			unionList = JointMembership.unionLists(listA, listB);
			printout("Union List", unionList);
		});

		Then("^new list contains all elements in ListA and ListB$", () -> {
			Assert.assertTrue("new list contains all elements in ListA", unionList.retainAll(listA));
			Assert.assertTrue("new list contains all elements in ListB", unionList.retainAll(listB));

		});

		Then("^new list contains no duplicates$", () -> {
			Set<Integer> buff = new HashSet<>();
			Assert.assertFalse("Union list contains not duplicates",
					unionList.stream().filter(val -> !buff.add(val)).findAny().isPresent());
		});

	}

	private void printout(String str, List<?> result) {
		scenario.write(String.format("    %s : %s", str, result));
	}
}
