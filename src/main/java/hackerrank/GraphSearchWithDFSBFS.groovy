package hackerrank
/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 31, 2017
 */
class Graph<E>{
	class Vertex<V>{
		V value
		List<Vertex> adjacent = new LinkedList()
		public Vertex(V value) {
			super()
			this.value = value
		}
		public String toString(){
			String adj = '['
			if(adjacent){
				adjacent.each { adj+="${it.value}," }
				adj = adj.substring(0, adj.length()-1)
			}
			adj +=']'
			return "${value}:${adj}"
		}
	}

	private Map<E, Vertex<E>> vertexMap = new HashMap()

	private Vertex<E> getVertex(E value){
		return vertexMap.get(value)
	}

	public void addEdge(E source, E destination){
		if(source == null || destination==null || source.equals(destination)){
			return
		}
		Vertex s = getVertex(source)
		Vertex d = getVertex(destination)
		if(!s){
			s = new Vertex(source)
			vertexMap.put(source, s)
		}
		if(!d){
			d = new Vertex(destination)
			vertexMap.put(destination, d)
		}
		if(!s.adjacent.contains(d)){
			s.adjacent.add(d)
		}
	}

	public boolean hasPathDFS(E source, E destination){
		Vertex s = getVertex(source)
		Vertex d = getVertex(destination)
		if(!s||!d){
			return false
		}
		return findPathDFS(s, d, new HashSet<Vertex<E>>())
	}

	private boolean findPathDFS(Vertex<E> source, Vertex<E> destination, Set<Vertex<E>> visited){
		if(visited.contains(source)){
			return false
		}
		visited.add(source)
		if(source.equals(destination)){
			return true
		}
		for(Vertex c : source.adjacent){
			if( findPathDFS(c, destination, visited)){
				return true
			}
		}
		return false
	}
	
	public boolean hasPathBFS(E source, E destination){
		Vertex s = getVertex(source)
		Vertex d = getVertex(destination)
		if(!s||!d){
			return false
		}
		return findPathBFS(s, d)
	}
	private boolean findPathBFS(Vertex<E> source, Vertex<E> destination){
		Queue<Vertex<E>> nextToVisit = new LinkedList()
		Set<Vertex<E>> visited = new HashSet<Vertex<E>>()
		nextToVisit.add(source)
		while(!nextToVisit.isEmpty()){
			Vertex<E> toVisit = nextToVisit.poll()
			if(visited.contains(toVisit)){
				println "Skip Visited: ${toVisit}"
				continue
			}
			println toVisit
			visited.add(toVisit)
			if(toVisit.equals(destination)){
				return true
			}
			nextToVisit.addAll(toVisit.adjacent)
		}
		return false
	}
	
}

Map<Integer, List<Integer>> inputData = new HashMap()
(0..10).each {
	List<Integer> dests = new LinkedList()
	(0..(Math.random()*5 as Integer)).each {
		dests.add(Math.random()*20 as Integer)
	}
	inputData.put(it, dests)
}

inputData = [0:[9, 11], 1:[14, 10, 2], 2:[16, 4, 14], 3:[18, 11], 4:[7, 12, 10], 5:[11, 3, 13], 6:[8, 2, 4, 13, 7], 7:[0, 1, 17, 12, 8], 8:[4], 9:[9, 1, 8], 10:[4]]


println inputData
Graph<Integer,Integer> graph = new Graph()
inputData.each { source, destinations ->
	destinations.each { destination ->
		graph.addEdge(source, destination)
	}
}
println graph.vertexMap.values()
println graph.hasPathBFS(1,13)

//(0..20).each {
//	boolean hasPathDFS = graph.hasPathDFS(1,it)
//	boolean hasPathBFS = graph.hasPathBFS(1,it)
//	println "graph hasPathDFS(1,${it}):${hasPathDFS}"
//	println "graph hasPathBFS(1,${it}):${hasPathBFS}"
//}
