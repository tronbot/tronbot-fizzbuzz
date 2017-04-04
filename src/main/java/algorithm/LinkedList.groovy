package algorithm

import java.util.LinkedList.Node

/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 27, 2017
 */
class TheLinkedList<T>{
	public TheLink<T> firstLink

	public boolean isEmpty(){
		return firstLink == null
	}

	public void insertFirstLink(T entity){
		TheLink<T> newLink = new TheLink(entity)
		newLink.next = firstLink
		firstLink = newLink
	}

	public TheLink removeFirstLink(){
		TheLink removed = firstLink
		firstLink = firstLink?.next
		return removed
	}
}

class TheLink<T>{
	T entity
	TheLink<T> next
	public TheLink(T entity){
		this.entity = entity
	}
}



class TheBiLink<T>{
	T entity
	TheBiLink<T> next
	TheBiLink<T> prev

	public TheBiLink(TheBiLink<T> prev, T entity, TheBiLink<T> next){
		this.prev = prev
		this.entity = entity
		this.next = next
	}
}


class TheBiLinkedList<T>{
	public TheBiLink<T> first
	public TheBiLink<T> last


	public void insertBefore(T entity){
		final TheBiLink<T> l = last;
		final TheBiLink<T> newLink = new TheBiLink(l, entity, null);
		last = newLink;
		if (l == null)
			first = newLink;
		else
			l.next = newLink;
	}

	public void insertAfter(T entity){
		final TheBiLink<T> f = first;
		final TheBiLink<T> newLink = new TheBiLink(null, entity, f);
		first = newLink;
		if (f == null)
			last = newLink;
		else
			f.prev = newLink;
	}
}


public static void displayLinkedList(TheLinkedList ll){
	TheLink currLink = ll.firstLink
	while(currLink){
		print currLink.entity
		currLink = currLink?.next
		if(currLink){
			print ', '
		}else{
			print '\n'
		}
	}
}

public static void displayBiLinkedList(TheBiLinkedList ll){
	TheBiLink currLink = ll.first
	while(currLink){
		print currLink.entity
		currLink = currLink?.next
		if(currLink){
			print ', '
		}else{
			print '\n'
		}
	}
}

TheLinkedList ll = new TheLinkedList()
(0..20).each{
	ll.insertFirstLink(Math.random()*100 as Integer)
}
displayLinkedList(ll)
println "Removed : ${ll.removeFirstLink()?.entity}"
println "Removed : ${ll.removeFirstLink()?.entity}"
displayLinkedList(ll)

println '---------------------------'

TheBiLinkedList bll = new TheBiLinkedList()

(0..20).each{
	Integer val = Math.random()*100 as Integer
	if(it%2 == 0){
		bll.insertBefore(val)
	}else{
		bll.insertAfter(val)
	}
	displayBiLinkedList(bll)
}