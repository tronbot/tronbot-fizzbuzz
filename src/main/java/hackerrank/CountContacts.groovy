package hackerrank
/**
 * https://www.hackerrank.com/challenges/ctci-contacts
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 29, 2017
 */
class TheTrie {
	private static final int NUMBER_OF_CHARACTERS = 26
	TheTrie[] children = new TheTrie[NUMBER_OF_CHARACTERS]
	private int size = 0

	public String toString(){
		char c = ('a' as char)+children.findIndexOf { return it != null }
		return "${c}*${size}"
	}

	private static int getCharIndex(char c){
		return c - ('a' as char)
	}

	private static int getCharIndex(String c){
		if(!c){
			throw new IllegalArgumentException("Input can't be null!")
		}
		return getCharIndex(c?.charAt(0))
	}

	private TheTrie getTheTrie(char c){
		return children[getCharIndex(c)]
	}

	private TheTrie setTheTrie(char c, TheTrie node){
		children[getCharIndex(c)] = node
	}


	public void add(String s){
		addWithIdx(s, 0)
	}

	private void addWithIdx(String s, int index){
		size++
		if(index == s.length())
			return
		char currentChar = s.charAt(index)
		TheTrie child = getTheTrie(currentChar) != null ? getTheTrie(currentChar) : new TheTrie()
		setTheTrie(currentChar, child)
		child.addWithIdx(s, index+1)
	}
	
	public int findCount(String s){
		return findCountWithIdx(s, 0)
	}
	
	private int findCountWithIdx(String s, int index){
		if(index == s.length()){
			return size
		}
		TheTrie child = getTheTrie(s.charAt(index))
		if(!child){
			return 0
		}
		return child.findCountWithIdx(s, index+1)
	}
}




String inputString = 
'''4
add ahack
add ahackerrank
find hac
find hak

'''

InputStream is = new ByteArrayInputStream(inputString.getBytes())
is = System.in
Scanner input = new Scanner(is)
TheTrie trie = new TheTrie()
while (input.hasNextLine()) {
	String ln = input.nextLine()
	if (!ln) {
		break
	}
	if(ln.startsWith('add ')){
		trie.add(ln.split(' ')[1])
	}else if(ln.startsWith('find ')){
		println trie.findCount(ln.split(' ')[1])
	}
}
is.closeQuietly()


