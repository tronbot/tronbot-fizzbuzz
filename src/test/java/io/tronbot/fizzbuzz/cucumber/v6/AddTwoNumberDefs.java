package io.tronbot.fizzbuzz.cucumber.v6;

import java.math.BigDecimal;

import org.junit.Assert;

import cucumber.api.java8.En;
import io.tronbot.fizzbuzz.v6.AddTwoNumber;

/**
 * 
 * @Author Juanyong Zhang
 * @Date Oct 23, 2016
 */
public class AddTwoNumberDefs implements En {

	private BigDecimal result1;
	private Throwable result2Err;
	private Throwable result3Err;

	public AddTwoNumberDefs() {
		Given("^input as \"([^\"]*)\" and \"([^\"]*)\"$", (String a, String b) -> {
			result1 = AddTwoNumber.add(a, b);
		});
		Then("^get return value \"([^\"]*)\"$", (String arg1) -> {
			BigDecimal expected = new BigDecimal(arg1);
			Assert.assertTrue(expected.equals(result1));
			;
		});

		Given("^invalid inputs as \"([^\"]*)\" and Null value$", (String a) -> {
			try {
				AddTwoNumber.add(a, null);
			} catch (Exception e) {
				result2Err = e;
			}
		});
		Then("^get IllegalArgumentException$", () -> {
			Assert.assertTrue(result2Err instanceof IllegalArgumentException);
		});
		
		
		Given("^invalid inputs as \"([^\"]*)\" and \"([^\"]*)\"$", (String a, String b) -> {
			try {
				AddTwoNumber.add(a, b);
			} catch (Exception e) {
				result3Err = e;
			}
		});

		Then("^get NumberFormatException$", () -> {
			Assert.assertTrue(result3Err instanceof NumberFormatException);
		});

	}
}
