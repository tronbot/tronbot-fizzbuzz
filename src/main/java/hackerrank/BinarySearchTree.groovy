package hackerrank

import hackerrank.Node.TraversalOrder

/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 31, 2017
 */
class Node<E extends Comparable>{
	Node<E> left, right
	E entity


	public Node(){
	}
	public Node(E entity){
		this.entity = entity
	}

	public void insert(E e){
		if(this.entity == null){
			this.entity = e
		}else if(e.compareTo(entity)<0){
			if(left){
				left.insert(e)
			}else{
				left = new Node(e)
			}
		}else{
			if(right){
				right.insert(e)
			}else{
				right = new Node(e)
			}
		}
	}

	public void insertRandomly(E e){
		if(this.entity == null){
			this.entity = e
		}else if(Math.random()<0.5){
			if(left){
				left.insert(e)
			}else{
				left = new Node(e)
			}
		}else{
			if(right){
				right.insert(e)
			}else{
				right = new Node(e)
			}
		}
	}


	public boolean contains(E e){
		if(!entity){
			return false
		}else if(entity.equals(e)){
			return true
		}else if(entity.compareTo(e)>0){
			if(left){
				return left.contains(e)
			}else{
				return false
			}
		}else if(entity.compareTo(e)<0){
			if(right){
				return right.contains(e)
			}else{
				return false
			}
		}
	}

	public void print(TraversalOrder order = TraversalOrder.InOrder){
		if(TraversalOrder.PreOrder.equals(order)){
			print "${entity} "
		}
		if(left){
			left.print(order)
		}
		if(TraversalOrder.InOrder.equals(order)){
			print "${entity} "
		}

		if(right){
			right.print(order)
		}
		if(TraversalOrder.PostOrder.equals(order)){
			print "${entity} "
		}
	}

	enum TraversalOrder{
		InOrder, PreOrder, PostOrder
	}
}

class MapKey<E extends Comparable> implements Comparable<MapKey>{
	E entity
	public MapKey(E entity){
		this.entity = entity
	}

	public int compareTo(MapKey o){
		if(!o){
			return -1
		}else if(entity.compareTo(o.entity) == 0){
			return -1
		}else{
			return entity.compareTo(o.entity)
		}
	}

	public boolean equals(Object obj) {
		if(!obj||!entity){
			return false
		}else{
			return (obj instanceof MapKey)&&(entity.equals(obj.entity))
		}
	}

	public String toString(){
		return entity?.toString()
	}
}


public <E extends Comparable> boolean checkBST(Node<E> root, E min, E max){
	if(null==root){
		return true
	}
	if((root.entity.compareTo(min)<0 || root.entity.compareTo(max)>0)){
		return false
	}
	return checkBST(root.left, min, root.entity) && checkBST(root.right,root.entity, max)
}


TreeMap<MapKey<Integer>, Integer> treeMap = new TreeMap()
Node<Integer> bst = new Node()
Node<Integer> tree = new Node()

(0..20).each {
	Integer entity = Math.random()*100 as Integer
	bst.insert(entity)
	tree.insertRandomly(entity)
	treeMap.put(new MapKey(entity), entity)
}

println treeMap.keySet().join(' ')
println "BST contains(55)?:${bst.contains(55)}"
bst.print()
println ''
tree.print()
println ''
println "BST is Binary Search Tree?:\t${checkBST(bst, Integer.MIN_VALUE, Integer.MAX_VALUE)}"
println "TREE is Binary Search Tree?:\t${checkBST(tree, Integer.MIN_VALUE, Integer.MAX_VALUE)}"


