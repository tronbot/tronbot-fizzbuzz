Feature: Question 7 - Joint Membership 
	Given two long lists of integers, A and B, use whatever tool/program you like
 	Background:
 		Given List A is array of divisible int by 3
 		Given List B is array of divisible int by 7
	Scenario: All elements present in both lists (elements must be present in list A and  list B)
		When find common elements as new list
		Then all new list elements is divisible by 3 and 7
	Scenario: All elements present in exactly one list, but not the other (“in A but not in B” as well as “in B but not in A”)
		When find listA exclusive elements as new list
		Then all new list elements is divisible by 7 but not 3
	Scenario: All elements present in any list, but discarding duplicates
		When union listA and listB as new list
		Then new list contains all elements in ListA and ListB
		And new list contains no duplicates 
	