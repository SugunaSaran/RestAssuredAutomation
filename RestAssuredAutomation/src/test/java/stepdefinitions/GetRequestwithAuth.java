package stepdefinitions;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;

import DataProvider.ConfigFileReader;
import DataProvider.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequestwithAuth {
	Response response;
	ConfigFileReader configReader=new ConfigFileReader();
	private String userid,userfirstname;
	//Scenario: Getting all users list
	@When("User sends the GET request to valid endpoint")
	public void user_sends_the_get_request_to_valid_endpoint() {
		response = RestAssured.given().header("Authorization", BackgroundStep.getAuthHeader()).when().get(RestAssured.baseURI+"uap/users");
	}

	@Then("The response should contain all users with status code as {int}.")
	public void the_response_should_contain_all_users_with_status_code_as(Integer int1) {
		assertEquals(int1, response.getStatusCode());
	}
	//Scenario: Getting all users list with an invalid endpoint
	@When("User sends the GET request an invalid endpoint")
	public void user_sends_the_get_request_an_invalid_endpoint() {
		response = RestAssured.given().header("Authorization", BackgroundStep.getAuthHeader()).when().get(RestAssured.baseURI+"uap/user");
	}
	//Getting all users list without an endpoint
	@When("User sends the GET request without an endpoint")
	public void user_sends_the_get_request_an_without_an_endpoint() {
		response = RestAssured.given().header("Authorization", BackgroundStep.getAuthHeader()).when().get(RestAssured.baseURI+"");
	}
	
	//Scenario: Check if the User is able to retrieve a user by User ID with Authorization.
	@Given("User Creates a GET request  with a valid User ID")
	public void user_creates_a_get_request_with_a_valid_user_id() {
		userid=PostRequestStep.getUserID();
	}
	@When("User sends the GET request to valid endpoint with user ID")
	public void user_sends_the_get_request_to_valid_endpoint_with_user_id_as() {
		//System.out.println("user id in when part of get request: "+userid);
		response = RestAssured.given().header("Authorization", BackgroundStep.getAuthHeader()).pathParam("userID", userid).when().get(RestAssured.baseURI+"uap/user/{userID}");
	}
	@Then("The response should contain a user detail with the User ID and with status code as {int}.")
	public void the_response_should_contain_a_user_detail_with_the_user_id_and_with_status_code_as(Integer int1) {
		assertEquals(int1, response.getStatusCode());
		assertEquals(Integer.parseInt(userid),response.jsonPath().getInt("user_id"));
	}
	@Then("The response should contain a status code as {int}.")
	public void the_response_should_contain_a_status_code_as(Integer int1) {
		assertEquals(int1, response.getStatusCode());
	}

	@Given("User Creates a GET request  with an empty User ID {string}.")
	public void user_creates_a_get_request_with_an_empty_user_id(String string) {
	    userid=string;
	}

	@Given("User Creates a GET request  with an invalid userID in {string} and {int}")
	public void user_creates_a_get_request_with_an_invalid_user_id_in_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "InvalidGetRequest");
	    userid=data.get(int1).get("Invalid User ID");
	}
	@Given("User Creates a GET request  with an invalid userFirstName in {string} and {int}")
	public void user_creates_a_get_request_with_an_invalid_user_first_name_in_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "InvalidGetRequest");
	    userfirstname=data.get(int1).get("Invalid User Name");
	}

	@When("User sends the GET request to valid endpoint with user firstname")
	public void user_sends_the_get_request_to_valid_endpoint_with_user_firstname() {
		response = RestAssured.given().header("Authorization", BackgroundStep.getAuthHeader()).pathParam("userFirstName", userfirstname).when().get(RestAssured.baseURI+"uap/users/username/{userFirstName}");
	}
	@Given("User Creates a GET request  with a valid User First Name")
	public void user_creates_a_get_request_with_a_valid_user_first_name() {
	    userfirstname=PostRequestStep.getUserFirstName();
	}



	@Then("The response should contain a user detail with the User firstname and with status code as {int}.")
	public void the_response_should_contain_a_user_detail_with_the_user_firstname_and_with_status_code_as(Integer int1) {
		assertEquals(int1, response.getStatusCode());
		
	}
	//Scenario: Check  If the get request is able to retrieve  a user by an empty User First Name.
	@Given("User Creates a GET request  with an empty User First Name as {string}.")
	public void user_creates_a_get_request_with_an_empty_user_first_name_as(String string) {
	    userfirstname=string;
	}

}
