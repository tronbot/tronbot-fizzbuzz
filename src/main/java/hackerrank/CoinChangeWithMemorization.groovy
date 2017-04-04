package hackerrank
/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 30, 2017
 */



long makeChange(int money, int[] coins){
	return makeChange(money, coins, 0, new HashMap<String, Long>())
}

long makeChange(int money, int[] coins, int idx, Map<String, Long> memo){
	if(money == 0){
		return 1
	}
	if(idx >= coins.length){
		return 0
	}
	String key = "${money}-${idx}"
	if(memo.containsKey(key)){
		return memo.get(key)
	}
	long ways = 0
	int amountWithCoins = 0
	while(amountWithCoins <= money){
		int remaining = money-amountWithCoins
		ways += makeChange(remaining, coins, idx+1, memo)
		amountWithCoins += coins[idx]
	}
	memo.put(key, ways)
	return ways
}


int[] coins = [25, 10, 5, 1]
int money = 100
println makeChange(money, coins)
