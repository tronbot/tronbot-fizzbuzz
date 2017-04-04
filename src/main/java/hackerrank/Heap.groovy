package hackerrank
/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 29, 2017
 */
class TheHeap<E extends Comparable> {
	private int capacity = 10
	private int size = 0
	E[] items = new E[capacity]

	private int getLeftChildIdx(int idx){
		return 2 * idx + 1
	}

	private int getRightChildIdx(int idx){
		return 2 * idx + 2
	}

	private int getParentIdx(int idx){
		return (idx - 1)/2
	}

	private boolean hasParent(int idx){
		return getParentIdx(idx) > -1
	}

	private boolean hasLeftChild(int idx){
		return getLeftChildIdx(idx) < size
	}

	private boolean hasRightChild(int idx){
		return getRightChildIdx(idx) < size
	}

	private E parent(int idx){
		return items[getParentIdx(idx)]
	}


	private void swap(int idx1, int idx2){
		E temp = items[idx1]
		items[idx1] = items[idx2]
		items[idx2] = temp
	}

	private void ensureCapacity(){
		if(size >= capacity){
			items = Arrays.copyOf(items, capacity * 2)
			capacity *= 2
		}
	}

	private void heapifyDown(){
		int idx = 0
		while(hasLeftChild(idx)){
			int smallerIdx = getLeftChildIdx(idx)
			if(hasRightChild(idx)&&items[getRightChildIdx(idx)].compareTo(items[smallerIdx])<0){
				smallerIdx = getRightChildIdx(idx)
			}
			swap(idx, smallerIdx)
			idx = smallerIdx
		}
	}


	private void heapifyUp(){
		int idx = size-1
		while(hasParent(idx) && parent(idx).compareTo(items[idx])>0){
			swap(getParentIdx(idx),idx)
			idx = getParentIdx(idx)
		}
	}

	public E peek(){
		if(size == 0 ) throw new IllegalStateException('Heap is empty!')
		return items[0]
	}

	public E poll(){
		if(size == 0 ) throw new IllegalStateException('Heap is empty!')
		E item = items[0]
		items[0] = items[size-1]
		size--
		heapifyDown()
		return item
	}

	public void add(E item){
		ensureCapacity()
		items[size] = item
		size++
		heapifyUp()
	}
}



TheHeap<Integer> h = new TheHeap()
(0..20).each {
	h.add(Math.random()*100 as Integer)
}
println "HEAP: ${h.items}"
println "Pollout: ${h.poll()}"
println "Pollout: ${h.poll()}"
println "HEAP: ${h.items}"
