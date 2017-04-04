Feature: Question 6 - Adding Two Numbers
	Write a Java program to add two numbers. Anticipate edge-case problems and future requirements for this program, and explain how you will adapt.
	Scenario: Valid inputs
		Given input as "3,400" and "230012.69"
		Then get return value "233412.69"
	Scenario: Handling null value
		Given invalid inputs as "3,400" and Null value
		Then get IllegalArgumentException
	Scenario: Handing non-numeric inputs
		Given invalid inputs as "ImNotANumber" and "230012"
		Then get NumberFormatException