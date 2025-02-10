@NoAuth
Feature: Checking different requests without Authorization
Background: 
 Given User sets No Authorization 

  Scenario: Getting all users list without Authorization
   
    When User sends the GET request to valid endpoint without Authorization
    Then The response's status code should be 401.
    
   Scenario: Check if the User is able to retrieve a user by User ID without Authorization
   	Given User Creates a GET request  with a valid User ID as "1" without Authorization
    When User sends the GET request to valid endpoint with user ID without Authorization
    Then The response's status code should be 401.
    
    Scenario: Check if the User is able to retrieve a user by User First Name without Authorization.
   	Given User Creates a GET request  with a valid User First Name as "Numpy" without Authorization
    When User sends the GET request to valid endpoint with user firstname without Authorization
    Then The response's status code should be 401.
    
   Scenario Outline:  Validating the new user creation with all field values without Authorization
   Given Give the request body with valid values "<sheetname>" and <rownumber> for all the fields without Authorization
	 When Send a post request to a valid endpoint without Authorization
	 Then The response's status code should be 401.
	 
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|1|