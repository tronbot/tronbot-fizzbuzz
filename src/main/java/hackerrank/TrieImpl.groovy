package hackerrank
/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Apr 3, 2017
 */
class TrieNode<E> {
	E entity
	Map<E, TrieNode<E>> children = new HashMap()
	int count = 1

	public TrieNode() {}

	public TrieNode(E entity){
		this.entity = entity
	}
	
	public String toString(){
		return "${entity}*${count}${children.keySet()}"
	}
}

public class Trie<E>{
	private TrieNode<E> root

	public Trie() {
		root = new TrieNode()
	}
	

	public void insert(E[] elements) {
		HashMap<E, TrieNode<E>> children = root.children
		elements.each { e->
			TrieNode t = null
			if(children.containsKey(e)){
				t = children.get(e)
				t.count++
			}else{
				t = new TrieNode(e)
				children.put(e, t)
			}
			children = t.children
		}
	}
	
	public int count(E[] elements){
		HashMap<E, TrieNode<E>> children = root.children
		TrieNode child = null
		elements.find { e->
			if(!children.containsKey(e)){
				child = null
				return true
			}else{
				child = children.get(e)
				children = child.children
				return false
			}
		}
		return child?child.count:0
	}
}


String[] inputs = ['sdadfadfad2dfa', 'sda4nsdqwef2dfa', 'sqtrhwdsdfad']


Trie<Character> tries = new Trie()
inputs.each {
	tries.insert(it.toCharArray())
}
println tries
println tries.count('sd'.toCharArray())

