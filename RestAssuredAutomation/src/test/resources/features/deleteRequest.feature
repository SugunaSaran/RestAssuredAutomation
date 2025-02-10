Feature: Get Request with Authorization
Background: 
 Given User sets Basic Auth as Authorization for request

 			@UserDeletion	
    Scenario: Check if the User is able to delete a user by chaining User ID with Authorization.
   	Given User Creates a DELETE request  with a valid User ID
    When User sends the DELETE request to valid endpoint with user ID
    Then The delete response should contain a status code as 200.

    Scenario: Check If the get request is able to delete a user by an empty User ID.
   	Given User Creates a DELETE request  with an empty User ID "".
    When User sends the DELETE request to valid endpoint with user ID
    Then The delete response should contain a status code as 400.
    
    Scenario Outline: Check If the get request is able to delete  a user with an invalid User ID.
   	Given User Creates a DELETE request  with an invalid userID in "<sheetName>" and <rowNumber> 
    When User sends the DELETE request to valid endpoint with user ID
    Then The delete response should contain a status code as 400.
    Examples:
    |sheetName|rowNumber|
	  |InvalidGetRequest|0|
	  |InvalidGetRequest|1|
	  |InvalidGetRequest|2|
	  
	 	  		
			@UserDeletion	
		Scenario Outline: Check if the User is able to delete a user by User First Name with Authorization.
   	Given User Creates a DELETE request  with a valid User First Name as "<sheetName>" and <rowNumber>
    When User sends the DELETE request to valid endpoint with user firstname
    Then The delete response should contain a status code as 200.
    Examples:
    |sheetName|rowNumber|
	  |PostRequestData|0|
	  |PostRequestData|1|

    Scenario: 

   	Given User Creates a DELETE request  with an empty User First Name as "".
    When User sends the DELETE request to valid endpoint with user firstname
    Then The delete response should contain a status code as 400.
    	 
	  Scenario Outline: Check If the DELETE request is able to delete a user with an invalid User FirstName.
   	Given User Creates a DELETE request  with an invalid userFirstName in "<sheetName>" and <rowNumber> 
    When User sends the DELETE request to valid endpoint with user firstname
    Then The delete response should contain a status code as 400.
    Examples:
    |sheetName|rowNumber|
	  |InvalidGetRequest|0|
	  |InvalidGetRequest|1|
	  |InvalidGetRequest|2|