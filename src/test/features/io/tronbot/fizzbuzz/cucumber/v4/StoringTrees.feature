Feature: Question 4 - Storing Trees
	Suppose you had a tree structure, where each node contains a geographic name
	(e.g., continent, country, state, etc.), and each node can have many children
	but exactly one parent. What database schema would you use to store such a
	structure? Given a particular node, how would you enumerate all of that
	node’s siblings?
	Scenario: Storing Trees
		Given entry as 1,1,"AMERICA"
		Given entry as 2,1,"USA"
		Given entry as 3,1,"CANADA"
		Given entry as 4,2,"NYC"
		Given entry as 5,2,"DC"
		Given entry as 6,3,"TORT"
		Given entry as 7,3,"VACR"
		Given entry as 8,2,"LA"
		Given entry as 9,2,"CHICAGO"
		When get Siblings of "DC" 
		Then I should get "NYC,LA,CHICAGO"
    	
	    #|	ID 	|	PARENT	|	VALUE	|
	    #| 	1 	| 		1 	|	AMERICA	|
	    #|	2	|		1	|	USA		|
	    #|	3	|		1	|	CANADA	|
	    #|	4	|		2	|	NYC		|
	    #|	5	|		2	|	DC		|
	    #|	6	|		3	|	TORT	|
	    #|	7	|		3	|	VACR	|
	    #|	8	|		2	|	LA		|
	    #|	9	|		2	|	CHICAGO	|