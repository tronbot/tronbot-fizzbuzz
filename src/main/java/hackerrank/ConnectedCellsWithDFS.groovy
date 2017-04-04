package hackerrank
/**
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 30, 2017
 */

private void randomPlace(String[][] matrix, double chance){
	for(int row = 0 ; row < matrix.length ; row ++){
		for(int col = 0 ; col < matrix[row].length; col++){
			if(Math.random()<chance){
				matrix[row][col] = 'X'
			}else{
				matrix[row][col] = 'O'
			}
		}
	}
}


private void visualize(String[][] matrix){
	for(int row = matrix.length-1 ; row >=0  ; row --){
		for(int col = 0 ; col < matrix[row].length; col++){
			print "[${matrix[row][col]}]"
		}
		println ''
	}
}


public int getBiggestRegionSize(String[][] matrix){
	int maxRegionSzie = 0
	for(int row = 0 ; row < matrix.length ; row ++){
		for(int col = 0 ; col < matrix[row].length; col++){
			if(matrix[row][col] == 'X'){
				int size = getRegionSize(matrix, row, col)
				maxRegionSzie = Math.max(maxRegionSzie, size)
			}
		}
	}
	return maxRegionSzie
}

public int getRegionSize(String[][] matrix, row, col){
	if(row<0||row>=matrix.length||col<0||col>=matrix[row].length){
		return 0
	}
	if(matrix[row][col]=='O'){
		return 0
	}
	matrix[row][col]='O' // avoid recalculation
	int size = 1
	for(int r = row-1;r <= row +1; r++){
		for(int c = col-1;c <= col +1; c++){
			if(r!=row||c!=col){
				size += getRegionSize(matrix, r, c)
			}
		}
	}
	return size
}


String[][] matrix = new String[10][10]
randomPlace(matrix, 0.3)
visualize(matrix)

println getBiggestRegionSize(matrix)
