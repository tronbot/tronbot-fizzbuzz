package io.tronbot.fizzbuzz.cucumber.v1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java8.En;
import io.tronbot.fizzbuzz.v1.TelephoneDirectory;

/**
 * 
 * @Author Juanyong Zhang
 * @Date Oct 23, 2016
 */
public class TelephoneDirectoryDefs implements En {
	private Scenario scenario;
	private static Random random = new Random();
	private TelephoneDirectory telephoneDirectory;
	private Path telephoneDirectoryPath;

	@Before
	public void before(Scenario scenario) {
		this.scenario = scenario;
	}

	public TelephoneDirectoryDefs() {

		Given("^generate 10mb file with dummy name and number$", () -> {
			telephoneDirectoryPath = generateTelephoneDirectory();
		});

		Given("^load telephone directory file$", () -> {
			
			telephoneDirectory = new TelephoneDirectory(telephoneDirectoryPath);
			scenario.write(String.format("\tDelete telephone directory file: %s", telephoneDirectoryPath));
		});

		Given("^delete the telephone directory file$", () -> {
			
			try {
				Files.deleteIfExists(telephoneDirectoryPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

		Then("^display #(\\d+) of phone number$", (Integer index) -> {
			String key = telephoneDirectory.getKey(index);
			String val = telephoneDirectory.get(key);
			scenario.write(String.format("\t#%s - Person: %s, Tel: %s", index, key, val));
		});

	}

	private Path generateTelephoneDirectory() {
		Path tmp = null;
		try {
			tmp = Files.createTempFile("telephone-directory", ".tmp");
			String telephoneDirectoryContent = mockTelephoneDirectory();
			Files.write(tmp, telephoneDirectoryContent.getBytes("utf-8"), StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		scenario.write(String.format("\tTelephone Directory is generated: %s", tmp));
		return tmp;
	}

	private String mockTelephoneDirectory() {
		final int lines = 500000;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lines; i++) {
			String lineTempl = i < lines - 1 ? "%s,(%s)-%s-%s\n" : "%s,(%s)-%s-%s";
			sb.append(String.format(lineTempl, randomString(), randomInt(200, 999), randomInt(100, 999),
					randomInt(1000, 9999)));
		}
		return sb.toString();
	}

	private String randomString() {
		return Long.toString(Math.abs(random.nextLong() % 3656158440062976L), 36);
	}

	private int randomInt(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}
}
