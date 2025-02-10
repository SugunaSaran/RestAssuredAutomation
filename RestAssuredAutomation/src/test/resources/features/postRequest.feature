Feature: Post Request with Authorization
Background: 
 Given User sets Basic Auth as Authorization for request
 
 	 
 	 @invalid 
   Scenario Outline: Validating the new user creation with all field values to an invalid endpoint
   Given Give the request body with valid values "<sheetname>" and <rownumber> for all the fields
	 When Send a post request to an invalid endpoint
	 Then The response status code should be 404
	 
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	
	  @invalid 
	 Scenario Outline:  Validating the new user creation with all field values with invalid JSON
   Given Give the request body with valid values "<sheetname>" and <rownumber> for all the fields and incorrect JSON format
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	 
	   @invalid 
	 Scenario Outline:  Validating the new user creation with only mandatory field values
   Given Give the request body with valid values "<sheetname>" and <rownumber> for only the mandatory fields
	 When Send a post request to a valid endpoint
	 Then The response status code should be 201. A response body should have auto-generated user id, address id , creation_time and last_mod_time field.
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	 
	 @invalid 
	 Scenario Outline:  Validating the new user creation with invalid user first name
   Given Give the request body with invalid value "<sheetname>" and <rownumber> for user_first_name field
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
    
    @invalid   
	 Scenario Outline:  Validation of missing user First name field 
   Given Give the request body "<sheetname>" and <rownumber> without first name field
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	   
	   @invalid  
	  Scenario Outline:  Validation of user First name field with empty string
   Given Give the request body with first name field "<sheetname>" and <rownumber> as empty string
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400. The message is "user firstname is mandatory and should contains alphabets only"
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	 
   @invalid  
   Scenario Outline: Validating the new user creation with invalid user last name
   Given Give the request body with invalid value for user_last_name field "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	 
	  @invalid 
	 Scenario Outline:  Validation of missing user last name field 
   Given Give the request body without last name field "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 
	 	 @invalid 
	 Scenario Outline:  Validation of user last name field with empty string
   Given Give the request body with last name field as empty string "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400. The message is "user last name is mandatory and should contains alphabets only"
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 
    @invalid 
   Scenario Outline:  Validating the new user creation with invalid contact number
   Given Give the request body with invalid value for contact number field "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 
	 
 @invalid 
	 Scenario Outline:  Validation of missing user contact number field 
   Given Give the request body without contact number field "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400 and "Phone Number is required and should contains 10 numeric values only"
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 
	 @invalid 
	 Scenario Outline:  Validation of contact number field with empty string
   Given Give the request body with contact number field as empty string "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400. The message is "user contact number is mandatory and should contains alphabets only"
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 
	 
	   @invalid 
	 Scenario Outline:  Validation of duplicate user contact number 
   Given Give the request body with duplicate contact number for user 1234567890 and other fields like "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 Then The response status code should be 409. The message is "User already exist with same contact number"
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	
	  @invalid 
	 Scenario Outline:  Validating the new user creation with invalid user email id
   Given Give the request body with invalid value for email id field "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	 
	  @invalid 
	 Scenario Outline:  Validation of missing user user email id field 
   Given Give the request body without email id field "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 
	 Then The response status code should be 400
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	 
	  @invalid 
	 Scenario Outline:  Validation of email id field with empty string
   Given Give the request body with email id field as empty string "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400. The message is "user email id is mandatory and should contains alphabets only"
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	 
	  @invalid 
	 Scenario Outline:  Validation of duplicate user email id 
   Given Give the request body with duplicate email id for user "Numpy@gmail.com" and other fields like "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 Then The response status code should be 409. The message is "User already exist with same email id"
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	 
	 @invalid 
   Scenario Outline:  Validating the user creation with invalid plotNumber in userAddress
   Given Give the request body with all valid values for mandatory field and invalid value for plotnumber field like "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400. The message should be "Plot number should contain alphaNumeric values only"
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	 
	  @invalid 
	 Scenario Outline:  Validating the user creation with invalid state in userAddress
   Given Give the request body with all valid values for mandatory field and invalid value for state field like "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400. The message should be "State should contain alphabet characters only"
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	
	 @invalid 	
	  Scenario Outline:  Validating the user creation with invalid country in userAddress
   Given Give the request body with all valid values for mandatory field and invalid value for country field like "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 Then The response status code should be 400. The message should be "Country should contain alphabet characters only"
	 
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	 
	  
	 @UserCreation 
	 Scenario Outline:  Validating the user creation with valid zipcode in userAddress
	 Given Give the request body with all valid values for mandatory field and valid value for zipcode field like "<sheetname>" and <rownumber>
	 When Send a post request to a valid endpoint
	 Then The response status code should be 201. A response body should have auto-generated user id, address id , creation_time and last_mod_time field.
	 
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
 
	 @UserCreation
	 Scenario Outline:  Validating the new user creation with all field values
   Given Give the request body with valid values "<sheetname>" and <rownumber> for all the fields
	 When Send a post request to a valid endpoint
	 Then The response status code should be 201. A response body should have auto-generated user id, address id , creation_time and last_mod_time field.
	 
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|1|
	 