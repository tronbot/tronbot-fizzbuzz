package io.tronbot.fizzbuzz.v2;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Write down the signature of a Java method that will take in a String and
 * return a bunch of Strings that are synonyms of the input String. The
 * implementation of the method is irrelevant. Elaborate on the choices you made
 * when selecting the type for the return value. Specifically, what benefits
 * does choosing this type over another give you?
 * 
 * @Author Juanyong Zhang
 * @Date Oct 24, 2016
 */
public class SynonymMap {
	private Map<String, Set<String>> synonymTable = new HashMap<>();

	public Set<String> getSynonyms(String word) {
		return synonymTable.get(word);
	}

	// register word and Synonym bi-directly
	public void registerSynonym(String word, String... synonymsOfWord) {
		for (String syn : synonymsOfWord) {
			putSynonymTable(word, syn); // synonym(String) = syn
			putSynonymTable(syn, word); // synonym(syn) = String
		}
	}

	// register word and Synonym bi-directly
	public void parseSynonym(String synonymEntry) {
		String[] synonymItem = synonymEntry.split(":");
		
		String word = synonymItem[0];
		String[] synonyms = synonymItem[1].substring(1, synonymItem[1].length()-1).split(",");
		registerSynonym(word, synonyms);
	}

	private void putSynonymTable(String word, String synonymOfWord) {
		Set<String> syns = synonymTable.get(word);
		if (syns == null) {
			syns = new TreeSet<String>();
			synonymTable.put(word, syns);
		}
		syns.add(synonymOfWord);
	}

}
