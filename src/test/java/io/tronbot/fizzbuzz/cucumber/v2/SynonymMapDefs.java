package io.tronbot.fizzbuzz.cucumber.v2;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import io.tronbot.fizzbuzz.v2.SynonymMap;

/**
 * 
 * @Author Juanyong Zhang
 * @Date Oct 23, 2016
 */
public class SynonymMapDefs implements En {
	private Scenario scenario;
	private SynonymMap synonymMap;

	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
	}

	public SynonymMapDefs() {
		Given("^create Synonyms Map$", () -> {
			synonymMap = new SynonymMap();
		});

		Given("^load \"([^\"]*)\"$", (String synonymStr) -> {
			synonymMap.parseSynonym(synonymStr);
		});

		Then("^display synonyms of \"([^\"]*)\"$", (String word) -> {
		    
			scenario.write(String.format("\tSynonyms of '%s' is %s", word, synonymMap.getSynonyms(word)));
		});

	}

}
