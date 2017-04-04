package io.tronbot.fizzbuzz.cucumber.v4;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import io.tronbot.fizzbuzz.v4.StoringTrees;

/**
 * Storing Trees
 * 
 * Suppose you had a tree structure, where each node contains a geographic name
 * (e.g., continent, country, state, etc.), and each node can have many children
 * but exactly one parent. What database schema would you use to store such a
 * structure? Given a particular node, how would you enumerate all of that
 * node’s siblings?
 * 
 * @Author Juanyong Zhang
 * @Date Oct 23, 2016
 */
public class StoringTreesDefs implements En {
	private Scenario scenario;
	private StoringTrees<String> storingTrees = new StoringTrees<>();
	private List<String> siblings;

	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
	}

	public StoringTreesDefs() {
		Given("^entry as (\\d+),(\\d+),\"([^\"]*)\"$", (Long id, Long parentId, String value) -> {
			storingTrees.put(id, parentId, value);
		});
		When("^get Siblings of \"([^\"]*)\"$", (String given) -> {
			scenario.write("CREATE TABLE Storing_Trees(");
			scenario.write("\tid INT AUTO_INCREMENT PRIMARY KEY,");
			scenario.write("\tvalue VARCHAR(20) NOT NULL,");
			scenario.write("\tparent INT DEFAULT NULL);");
			scenario.write("\t#|\tID\t|\tPARENT\t|\tVALUE\t|");
			scenario.write("\t#|\t1\t|\t1\t|\tAMERICA\t|");
			scenario.write("\t#|\t2\t|\t1\t|\tUSA\t|");
			scenario.write("\t#|\t3\t|\t1\t|\tCANADA\t|");
			scenario.write("\t#|\t4\t|\t2\t|\tNYC\t|");
			scenario.write("\t#|\t5\t|\t2\t|\tDC\t|");
			scenario.write("\t#|\t6\t|\t3\t|\tTORT\t|");
			scenario.write("\t#|\t7\t|\t3\t|\tVACR\t|");
			scenario.write("\t#|\t8\t|\t2\t|\tLA\t|");
			scenario.write("\t#|\t9\t|\t2\t|\tCHICAGO\t|");
			siblings = storingTrees.getSiblings(given);
		});

		Then("^I should get \"([^\"]*)\"$", (String expected) -> {
			assertTrue(siblings.toString().equals(Arrays.asList(expected.split(",")).toString()));
		});
	}
}
