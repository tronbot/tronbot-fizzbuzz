package hackerrank
/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Apr 3, 2017
 */
long makeChange(int money, int[] coins, int coinIdx, Map<String, Long> memo){
	if(money == 0){
		return 1
	}
	if(coinIdx >= coins.length){
		return 0
	}
	String key = "${money}-${coinIdx}"
	if(memo.containsKey(key)){
		return memo.get(key)
	}
	long ways = 0
	int amountWithCoins = 0
	while(amountWithCoins <= money){
		int change = money - amountWithCoins
		ways+=makeChange(change, coins, coinIdx+1, memo)
		amountWithCoins += coins[coinIdx]
	}
	
	memo.put(key, ways)
	return ways
}


long makeChange(int money, int[] coins){
	return makeChange(money, coins, 0, new HashMap<String, Long>())
}


int[] coins = [25, 10, 5, 1]
int money = 50

Scanner input = new Scanner(System.in)
String firstLn = input.nextLine()
String secLn = input.nextLine()
money = firstLn.split(' ')[0] as Integer
coins = new int[firstLn.split(' ')[1] as Integer] 

secLn.split(' ').eachWithIndex { val, idx ->
	coins[idx] = val as Integer
}



println makeChange(money, coins)