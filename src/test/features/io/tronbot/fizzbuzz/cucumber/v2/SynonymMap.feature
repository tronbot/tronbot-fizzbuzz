Feature: Question 2 - Synonyms Map
	 Write down the signature of a Java method that will take in a String and
	 return a bunch of Strings that are synonyms of the input String. The
	 implementation of the method is irrelevant. Elaborate on the choices you made
	 when selecting the type for the return value. Specifically, what benefits
	 does choosing this type over another give you?
	Scenario: Synonyms Map Implementation
		Given create Synonyms Map
			And load "hard:[arduous,backbreaking,difficult,fermented,firmly,grueling,gruelling,heavily,heavy,intemperately,knockout,laborious,punishing,severe,severely,strong,toilsome,tough]"
			And load "woods:[forest,wood]"
			And load "forest:[afforest,timber,timberland,wood,woodland,woods]"
			And load "wolfish:[edacious,esurient,rapacious,ravening,ravenous,voracious,wolflike]"
		Then display synonyms of "timber"
			And display synonyms of "forest"
			And display synonyms of "wood"
		
