package hackerrank
/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Apr 3, 2017
 */

public int getCharIndex(char c){
	return  c - ('a' as char)
}

//List<Character> input = new ArrayList()
//(0..50).each {
//	input.add((('a' as char) + (Math.random()*26 as Integer)) as char)
//}
//Collections.sort(input)
//def inputString = input.join('')
////inputString = 'ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd'
//inputString = 'abbbbccccdddd'
inputString = (new Scanner(System.in)).nextLine()

int[] charArrayCount = new int[26]

for(int i = 0; i<inputString.length(); i++){
	char c = inputString.charAt(i)
	charArrayCount[getCharIndex(c)]++
	int charIdx = getCharIndex(c)
}


boolean isValid = true
int i = 0
int inValids = 0
for(int j=i+1;j<charArrayCount.length;j++){
	if(charArrayCount[i]>0&&charArrayCount[j]>0){
		if((charArrayCount[i] == 1 || charArrayCount[j] == 1) && charArrayCount[i] != charArrayCount[j]){
			inValids += 1
		}else{
			inValids += Math.abs(charArrayCount[i]-charArrayCount[j])
		}
		
//		println "${i}-${(('a' as char)+i) as char}*${charArrayCount[i]} vs ${j}-${(('a' as char)+j) as char}*${charArrayCount[j]}:${inValids}"
		if(inValids>1&&inValids!=j){
			isValid = false
			break
		}
	}
}

println isValid?'YES':'NO'

