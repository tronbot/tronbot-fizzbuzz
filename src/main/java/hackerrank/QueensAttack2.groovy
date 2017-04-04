package hackerrank
/**
 * https://www.hackerrank.com/challenges/queens-attack-2 
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 22, 2017
 */
class Chessboard{
	Integer rows
	Queen queen
	SortedMap<Long, Obstacle> obstacleMap = new TreeMap()
	String debugs = '\n'

	static Chessboard init(Integer[][] initData){
		if(initData.length < 2 || initData[0].length != 2){
			throw new IllegalArgumentException("initData array length must great than or equal 2!")
		}
		initData = resetPositions(initData)

		Chessboard cb = new Chessboard(initData[0][0])
		cb.placeQueen(new Queen(initData[1][0], initData[1][1]))
		if(initData.length>2){
			Integer[][] obstacles = initData[2..initData.length-1]
			obstacles.each {obstacle ->
				cb.placeObstacles(new Obstacle(obstacle[0], obstacle[1]))
			}
		}
		return cb
	}

	private static Integer[][] resetPositions(Integer[][] initData){
		initData[0][0]--
		initData[1..initData.length-1].each { position ->
			position[0]--
			position[1]--
		}
		return initData
	}

	public Chessboard(Integer rows) {
		super()
		this.rows = rows
	}

	public void placeQueen(Queen queue){
		this.queen = queue
	}

	public void placeObstacles(Obstacle... obstacles){
		if(!obstacles){
			return
		}
		obstacles.each{ obstacle ->
			obstacleMap.put(obstacle.getKey(), obstacle)
		}
	}

	private Obstacle findObstacleBy(Integer row, Integer col){
		if(!obstacleMap){
			return null
		}else{
			return obstacleMap.get((new Obstacle(row,col)).getKey())
		}
	}

	public int attackPositionCount(){
		LinkedList<Position> path = new LinkedList()
		LinkedList<Position> rowPath = new LinkedList()
		LinkedList<Position> colPath = new LinkedList()
		LinkedList<Position> bsPath = new LinkedList()
		LinkedList<Position> fsPath = new LinkedList()
		//9:00
		if(queen.col>1)
			(queen.col-1..0).find {
				if(findObstacleBy(queen.row, it))
					return true
				colPath.addFirst(new Position(queen.row, it))
				return false
			}
		//3:00
		if(queen.col+1<rows)
			(queen.col+1..rows).find {
				if(findObstacleBy(queen.row, it))
					return true
				colPath.addLast(new Position(queen.row, it))
				return false
			}
		//6:00
		if(queen.row>1)
			(queen.row-1..0).find {
				if(findObstacleBy(it, queen.col))
					return true
				rowPath.addFirst(new Position(it, queen.col))
				return false
			}
		//12:00
		if(queen.row<rows)
			(queen.row+1..rows).find {
				if(findObstacleBy(it, queen.col))
					return true
				rowPath.addLast(new Position(it, queen.col))
				return false
			}
		// 7:30
		if(queen.col > 1 && queen.row>1){
			Integer col = queen.col
			Integer row = queen.row
			(queen.col-1..0).find {
				if(!inScope(--row, --col)||findObstacleBy(row,col))
					return true
				fsPath.addFirst(new Position(row, col))
				return false
			}
		}
		// 1:30
		if(queen.col+1<rows  && queen.row<rows){
			Integer col = queen.col
			Integer row = queen.row
			(queen.col+1..rows).find {
				if(!inScope(++row, ++col)||findObstacleBy(row,col))
					return true
				fsPath.addLast(new Position(row, col))
				return false
			}
		}
		//10:30
		if(queen.col>1&&queen.row<rows){
			Integer col = queen.col
			Integer row = queen.row
			(queen.col-1..0).find {
				if(!inScope(++row, --col)||findObstacleBy(row,col))
					return true
				bsPath.addFirst(new Position(row, col))
				return false
			}
		}

		//4:30
		if(queen.row>1&&queen.col+1<rows){
			Integer col = queen.col
			Integer row = queen.row
			(queen.col+1..rows).find {
				if(!inScope(--row, ++col)||findObstacleBy(row,col))
					return true
				bsPath.addLast(new Position(row, col))
				return false
			}
		}
		//				debugs += '\n'
		//				debugs += "xPath -\t${xPath.size()}: ${xPath.join('')}\n"
		//				debugs += "yPath -\t${yPath.size()}: ${yPath.join('')}\n"
		//				debugs += "fPath -\t${fsPath.size()}: ${fsPath.join('')}\n"
		//				debugs += "bPath -\t${bsPath.size()}: ${bsPath.join('')}\n"
		return colPath.size() + rowPath.size() + bsPath.size() + fsPath.size()
	}

	private Boolean inScope(row, col){
		return row>=0 && row<=rows && col>=0 && col<=rows
	}


	public Integer visualization(){
		Integer count = 0
		(rows..0).each  { row ->
			(0..rows).each { col ->
				String symbol = ' '
				if(col == queen.col && row == queen.row){
					symbol = 'Q'
				}else{
					this.obstacleMap.values()?.each { obstacle ->
						if(col == obstacle.col && row == obstacle.row){
							symbol = 'X'
						}
					}
					if(symbol == ' ' 	//virgin here
					&& (col == queen.col 	//same vertical
					|| row == queen.row 	//same horizontal
					|| Math.abs(col - queen.col) == Math.abs(row - queen.row) // in the X lines
					)){
						if(isReachable(row, col)){
							symbol = 'O'
						}
					}
				}
				if(symbol == 'O'){
					count++
				}
				debugs += "[${symbol}]"
			}
			debugs += '\n'
		}
		return count
	}


	public Boolean isReachable(Integer row, Integer col){
		Boolean reachable = true
		this.obstacleMap.values()?.each { obstacle ->
			if(
			(obstacle.col == col && ((obstacle.row < queen.row && obstacle.row >= row) || (obstacle.row > queen.row && obstacle.row <= row)))
			||(obstacle.row == row && ((obstacle.col < queen.col && obstacle.col >= col) || (obstacle.col > queen.col && obstacle.col <= col)))
			||(Math.abs(col - obstacle.col) == Math.abs(row - obstacle.row) && ((obstacle.row < queen.row &&  row < obstacle.row) || (obstacle.row > queen.row &&  row > obstacle.row)))
			)
			{
				reachable = false
			}
		}
		return reachable
	}
}

class Position{
	Integer row
	Integer col

	public Position(Integer row,Integer col) {
		super()
		this.row = row
		this.col = col
	}

	public String toString(){
		return "${this.getClass().getSimpleName()}@[${this.row + 1}, ${this.col + 1}]"
	}
}

class Queen extends Position{
	public Queen(Integer row,Integer col){
		super(row,col)
	}
}

class Obstacle extends Position{
	public Obstacle(Integer row,Integer col){
		super(row,col)
	}

	public Long getKey(){
		return row*1000000+col
	}
}


private static Integer[][] fromSystemIn(InputStream is){
	List<Integer[]> inputData = new ArrayList()
	Scanner input = new Scanner(System.in)
	while (input.hasNextLine()) {
		String lineNew = input.nextLine()
		if (lineNew.isEmpty()) {
			break
		}
		inputData << [
			lineNew.split(' ')[0] as Integer,
			lineNew.split(' ')[1] as Integer
		]
	}
	return inputData as Integer[][]
}
String testInput = '''
10 3
7 4
5 2
2 4
7 7
3 8
9 6
8 4
9 2

'''
Integer[][] initData = [
	[100, 100],
	[48, 81],
	[54, 87],
	[64, 97],
	[42, 75],
	[32, 65],
	[42, 87],
	[32, 97],
	[54, 75],
	[64, 65],
	[48, 87],
	[48, 75],
	[54, 81],
	[42, 81],
	[45, 17],
	[14, 24],
	[35, 15],
	[95, 64],
	[63, 87],
	[25, 72],
	[71, 38],
	[96, 97],
	[16, 30],
	[60, 34],
	[31, 67],
	[26, 82],
	[20, 93],
	[81, 38],
	[51, 94],
	[75, 41],
	[79, 84],
	[79, 65],
	[76, 80],
	[52, 87],
	[81, 54],
	[89, 52],
	[20, 31],
	[10, 41],
	[32, 73],
	[83, 98],
	[87, 61],
	[82, 52],
	[80, 64],
	[82, 46],
	[49, 21],
	[73, 86],
	[37, 70],
	[43, 12],
	[94, 28],
	[10, 93],
	[52, 25],
	[50, 61],
	[52, 68],
	[52, 23],
	[60, 91],
	[79, 17],
	[93, 82],
	[12, 18],
	[75, 64],
	[69, 69],
	[94, 74],
	[61, 61],
	[46, 57],
	[67, 45],
	[96, 64],
	[83, 89],
	[58, 87],
	[76, 53],
	[79, 21],
	[94, 70],
	[16, 10],
	[50, 82],
	[92, 20],
	[40, 51],
	[49, 28],
	[51, 82],
	[35, 16],
	[15, 86],
	[78, 89],
	[41, 98],
	[70, 46],
	[79, 79],
	[24, 40],
	[91, 13],
	[59, 73],
	[35, 32],
	[40, 31],
	[14, 31],
	[71, 35],
	[96, 18],
	[27, 39],
	[28, 38],
	[41, 36],
	[31, 63],
	[52, 48],
	[81, 25],
	[49, 90],
	[32, 65],
	[25, 45],
	[63, 94],
	[89, 50],
	[43, 41]
]
//Integer[][] initData = [[20, 12], [10, 10], [4, 4], [10, 4], [19, 1], [7, 10], [19, 10], [7, 11], [8, 11], [4, 16], [6, 16], [10, 16], [16, 16], [9, 19]]
//Integer[][] initData = [[20, 12], [10, 11], [1, 3], [1, 14], [4, 4], [10, 4], [19, 1], [7, 10], [19, 10], [7, 11], [8, 11], [4, 16], [6, 16], [10, 16], [16, 16], [9, 19]]
//Integer[][] initData =[[4, 0], [4, 4]]
//Integer[][] initData = [[5, 3], [4, 3], [5, 5], [4, 2], [2, 3]]
//println initData
initData = fromSystemIn(System.in)
Chessboard chessboard = Chessboard.init(initData)
println chessboard.attackPositionCount()
chessboard.visualization()
println chessboard.debugs