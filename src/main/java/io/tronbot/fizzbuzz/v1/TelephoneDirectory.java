package io.tronbot.fizzbuzz.v1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * Telephone Directory
 * 
 * Suppose I have a very long list of alphabetically-sorted unique names, along
 * with exactly one phone number next to each name. The list is very long,
 * containing ~10M names and numbers. Given a name, how would you find the
 * associated number without hitting disk every time? (Okay to hit disk for
 * one-time pre-processing.)
 * 
 * @Author Juanyong Zhang
 * @Date Oct 23, 2016
 */
public class TelephoneDirectory {
	private Map<String, String> map;
	private String[] keyArray;

	public TelephoneDirectory(Path contentPath) {
		SortedMap<String, String> buff = new TreeMap<>();
		try (Stream<String> stream = Files.lines(contentPath)) {
			stream.forEach(line -> {
				String[] item = line.split(",");
				buff.put(item[0], item[1]);
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		map = Collections.unmodifiableSortedMap(buff);
	}

	public String get(String key) {
		return map.get(key);
	}

	public String get(int index) {

		return get(getKey(index));
	}

	public String getKey(int index) {
		if (index < 0 || index > map.size() - 1) {
			return null;
		}
		keyArray = keyArray == null ? map.keySet().toArray(new String[map.size()]) : keyArray;
		return keyArray[index];
	}
}
