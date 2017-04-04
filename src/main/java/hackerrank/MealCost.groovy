package hackerrank
/**
 * https://www.hackerrank.com/challenges/30-operators
 * @author <a href="mailto:juanyong.zhang@gmail.com">Juanyong Zhang</a> 
 * @date Mar 31, 2017
 */



Scanner input = new Scanner(System.in)
double mealcost = input.nextLine() as Double
int tipPercentage = input.nextLine()  as int
int taxPercentage = input.nextLine()  as int

println "The total meal cost is ${Math.round(mealcost*(1+((tipPercentage+taxPercentage)/100)))} dollars."