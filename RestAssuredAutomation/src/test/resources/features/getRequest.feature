Feature: Get Request with Authorization
Background: 
 Given User sets Basic Auth as Authorization for request

  Scenario: Getting all users list
   
    When User sends the GET request to valid endpoint 
    Then The response should contain all users with status code as 200.
    
   Scenario: Getting all users list with invalid endpoint
   
    When User sends the GET request an invalid endpoint 
    Then The response should contain a status code as 404.
   @valid 
  Scenario: Getting all users list without an endpoint
   
    When User sends the GET request without an endpoint
    Then The response should contain all users with status code as 404.
    @valid
    Scenario: Check if the User is able to retrieve a user by User ID with Authorization.
   	Given User Creates a GET request  with a valid User ID
    When User sends the GET request to valid endpoint with user ID
    Then The response should contain a user detail with the User ID and with status code as 200.
		@valid
    Scenario: Check  If the get request is able to retrieve  a user by an empty User ID.
   	Given User Creates a GET request  with an empty User ID "".
    When User sends the GET request to valid endpoint with user ID
    Then The response should contain a status code as 404.
   	@invalid 
    Scenario Outline: Check  If the get request is able to retrieve  a user with an invalid User ID.
   	Given User Creates a GET request  with an invalid userID in "<sheetName>" and <rowNumber> 
    When User sends the GET request to valid endpoint with user ID
    Then The response should contain a status code as 400.
    Examples:
    |sheetName|rowNumber|
	  |InvalidGetRequest|0|
	  |InvalidGetRequest|1|
	  |InvalidGetRequest|2|
	  
	 	@valid
		Scenario: Check if the User is able to retrieve a user by User First Name with Authorization.
   	Given User Creates a GET request  with a valid User First Name
    When User sends the GET request to valid endpoint with user firstname
    Then The response should contain a user detail with the User firstname and with status code as 200.
    
		@invalid
    Scenario: 
   	Given User Creates a GET request  with an empty User First Name as "".
    When User sends the GET request to valid endpoint with user firstname
    Then The response should contain a status code as 404.
   
   	@invalid 	 
	  Scenario Outline: Check  If the get request is able to retrieve  a user with an invalid User FirstName.
   	Given User Creates a GET request  with an invalid userFirstName in "<sheetName>" and <rowNumber> 
    When User sends the GET request to valid endpoint with user firstname
    Then The response should contain a status code as 404.
    Examples:
    |sheetName|rowNumber|
	  |InvalidGetRequest|0|
	  |InvalidGetRequest|1|
	  |InvalidGetRequest|2|