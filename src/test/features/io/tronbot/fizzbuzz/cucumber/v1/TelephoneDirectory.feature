Feature: Question 1 - Telephone Directory
	 Suppose I have a very long list of alphabetically-sorted unique names, along
	 with exactly one phone number next to each name. The list is very long, containing ~10M names
	 and numbers. Given a name, how would you find the associated number without hitting disk
	 every time? (Okay to hit disk for one-time pre-processing.)
	Scenario: Telephone Directory Implementation
		Given generate 10mb file with dummy name and number
			And load telephone directory file
			And delete the telephone directory file
		Then display #10 of phone number
			And display #100 of phone number
			And display #300000 of phone number
		
