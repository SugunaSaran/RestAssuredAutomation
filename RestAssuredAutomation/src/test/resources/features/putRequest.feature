

Feature: Put Request with Authorization
Background: 
 Given User sets Basic Auth as Authorization for request
 
 		@valid
	 Scenario Outline:  Validating the updation of user with all field values
   Given Give the updated request body with valid values for all fields "<sheetname>" and <rownumber> with a  valid user id 
   When Send a put request to a valid endpoint
	 Then The response status code should be 200 and the response body should have auto-generated user id, address id , creation_time and last_mod_time field.
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|2|
	 
	  @invalid 
	 Scenario Outline:  Validation of duplicate user contact number while updating the record 
   Given Give the updated request body with duplicate contact number for user 1234567890 and other fields like "<sheetname>" and <rownumber>
	 When Send a put request to a valid endpoint
	 Then The response status code should be 400 for update.
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	
	  @invalid 
	 Scenario Outline:  Validating the  user updation with invalid user email id
   Given Give the updated request body with invalid value for email id field "<sheetname>" and <rownumber>
	 When Send a put request to a valid endpoint
	 Then The response status code should be 400 for update.
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|
	 
	 @invalid 
	 Scenario Outline: Validation of duplicate user email id while updating the record
   Given Update the request body with duplicate email id for user "Numpy@gmail.com" and other fields like "<sheetname>" and <rownumber>
	 When Send a put request to a valid endpoint
	 Then The response status code should be 400 for update.
	 Examples:
	 |sheetname|rownumber|
	 |PostRequestData|0|
	 |PostRequestData|1|