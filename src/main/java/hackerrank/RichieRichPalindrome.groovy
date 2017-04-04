package hackerrank
/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Apr 3, 2017
 */

//String temp = ''
//(0..11).each{
//	temp += (Math.random()*10 as Integer)
//}
////println temp
String firstLn = '13 6'
String secLn = '1463274706691'
Scanner input = new Scanner(System.in)
//String firstLn = input.nextLine()
//String secLn = input.nextLine().trim()
int numOfDigis = firstLn.split(' ')[0] as Integer
int mods = firstLn.split(' ')[1] as Integer
int[] digis = new int[numOfDigis]
for(int i ; i < secLn.length(); i++){
	digis[i] = (secLn.charAt(i) as String) as Integer

}

println maxPalindrome(digis, numOfDigis, mods)

def int fill(int[] arry,int val1, int val2,int idx1, int idx2, int mods){
	if(idx1 == idx2 && mods != 0){
		if(arry[idx1]!=9){
			arry[idx1] = 9
			return 1
		}else{
			return 0
		}
	} else if(mods == 2){
		int val = Math.max(val1, val2)
		if(val!=9){
			val = 9
			arry[idx1] = val
			arry[idx2] = val
			return 2
		}else{
			arry[idx1] = val
			arry[idx2] = val
			return 1
		}
	}else if(mods == 1){
		int val = Math.max(val1, val2)
		arry[idx1] = val
		arry[idx2] = val
		return 1
	}else if(mods == 0){
		arry[idx1] = arry[idx1]==-1?val1:arry[idx1]
		arry[idx2] = arry[idx2]==-1?val2:arry[idx2]
	}
	println arry
	return mods
}

def int fillLarger(int[] array, int val1,int val2, int... positions){
	int mod = 0
	int val = Math.max(val1, val2)
	if(val1!=val2){
		mod = 1
		positions.each { array[it] = val }
	}
	return mod
}

def int fillMax(int[] array, int val1,int val2, int... positions){
	int mod = 0
	if(val1 == 9 && val2 == 9){
		mod = 0
	}else if(val1 == 9 || val2 == 9){
		mod = 1
	}else{
		mod = 2
	}
	positions.each { array[it] = 9 }
	return mod
}

def String maxPalindrome(int[] digis, int numOfDigis, int mods){
	int[] palindromes = new int[numOfDigis]
	Arrays.fill(palindromes, -1)
	for(int lIdx = 0 ; lIdx < numOfDigis/2; lIdx++){
		int rIdx = numOfDigis-lIdx-1
		int l = digis[lIdx]
		int r = digis[rIdx]
		mods -= fillLarger(palindromes, r, l, lIdx, rIdx)
		println "${l}@${lIdx}:${r}@${rIdx}"
	}
	if(mods < 0){
		return -1
	}
	println "mods left:${mods}"
	println "digis : ${digis}"

	for(int lIdx = 0 ; lIdx < numOfDigis/2; lIdx++){
		int rIdx = numOfDigis-lIdx-1
		int l = digis[lIdx]
		int r = digis[rIdx]
		if(mods>=2){
			mods -= fillMax(palindromes,l,r,lIdx,rIdx)
		}else if(mods==1&&palindromes[l]!=-1){
			mods -= fillMax(palindromes,9,r,lIdx,rIdx)
		}else if(palindromes[l]==-1){
			palindromes[lIdx] = l
			palindromes[rIdx] = r
		}
	}
	println "palind: ${palindromes}"

	return palindromes.join('') 
}

/**
 * 1463274706691
 * ->
 * [1463274] --|
 * [706691]  --|->[mod:8]->
 */

