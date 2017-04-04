package hackerrank
/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 30, 2017
 */

private static Integer getDelta(Map<Character, Integer> mapA, Map<Character, Integer> mapB){
	if(!mapA || !mapB){
		return -1
	}
	Integer delta = 0
	Set<Character> keys = new HashSet()
	keys.addAll(mapA.keySet())
	keys.addAll(mapB.keySet())
	for(Character key: keys){
		Integer countA = mapA.containsKey(key)?mapA.get(key):0
		Integer countB = mapB.containsKey(key)?mapB.get(key):0
		Integer diff = Math.abs(countA-countB)
		println "Diff[${diff}]: ${key}*${countA} vs ${key}*${countB}"
		delta += diff
	}
	return delta
}

private static Map<Character, Integer> getCharCountMap(String str){
	Map<Character, Integer> chartCountMap = new TreeMap()
	if(str){
		for(int idx =0; idx<str.length();idx++){
			Character key = str[idx]
			Integer count = chartCountMap.containsKey(key)?chartCountMap.get(key)+1:1
			chartCountMap.put(key, count)
		}
	}
	return chartCountMap
}

public static Integer numberNeeded(String a, String b){
	Map<Character, Integer> charCountMapA = getCharCountMap(a)
	Map<Character, Integer> charCountMapB = getCharCountMap(b)
	return getDelta(charCountMapA, charCountMapB)
}

String a = 'County Of Humboldt Mental Health Branch'
String b = 'Santa Barbara County Alcohol, Drug and Mental Health Services'
println numberNeeded(a, b)