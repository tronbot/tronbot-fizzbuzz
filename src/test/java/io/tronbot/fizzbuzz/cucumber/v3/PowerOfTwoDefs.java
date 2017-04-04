package io.tronbot.fizzbuzz.cucumber.v3;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import io.tronbot.fizzbuzz.v3.PowerOfTwo;

/**
 * 
 * @Author Juanyong Zhang
 * @Date Oct 23, 2016
 */
public class PowerOfTwoDefs implements En {
	private Scenario scenario;

	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
	}

	public PowerOfTwoDefs() {
		When("^input number as (\\d+) returns$", (Long num) -> {
			
			boolean isPowerOf2 = PowerOfTwo.check(num);
			scenario.write(isPowerOf2 ? String.format("%s is power of 2\t\t\t\t¯\\_(^_^)_/¯", num)
					: String.format("%s is not power of 2\t\t\t\t¯\\_(-_-)_/¯", num));
		});
	}

}
