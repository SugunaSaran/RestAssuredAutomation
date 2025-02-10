package stepdefinitions;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import DataProvider.ConfigFileReader;
import DataProvider.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class NoAuthScenarios {
	Response response;
	ConfigFileReader configReader=new ConfigFileReader();
	private Map<String, Object> requestBody=new HashMap<>();
	private Map<String,Object> userAddress=new HashMap<>();
	private String userid,userfirstname;
	@Given("User Creates a GET request  with a valid User ID as {string} without Authorization")
	public void user_creates_a_get_request_with_a_valid_user_id_as_without_authorization(String string) {
		userid=string;
	}

	@When("User sends the GET request to valid endpoint with user ID without Authorization")
	public void user_sends_the_get_request_to_valid_endpoint_with_user_id_without_authorization() {
		response = RestAssured.given().pathParam("userID", userid).when().get(RestAssured.baseURI+"uap/user/{userID}");
	}

	@Then("The response's status code should be {int}.")
	public void the_response_s_status_code_should_be(Integer int1) {
	    assertEquals(response.getStatusCode(),int1);
	}

	@Given("User Creates a GET request  with a valid User First Name as {string} without Authorization")
	public void user_creates_a_get_request_with_a_valid_user_first_name_as_without_authorization(String string) {
	    userfirstname=string;
	}

	@When("User sends the GET request to valid endpoint with user firstname without Authorization")
	public void user_sends_the_get_request_to_valid_endpoint_with_user_firstname_without_authorization() {
		response = RestAssured.given().pathParam("userFirstName", userfirstname).when().get(RestAssured.baseURI+"uap/users/username/{userFirstName}");
	}

	@Given("Give the request body with valid values {string} and {int} for all the fields without Authorization")
	public void give_the_request_body_with_valid_values_and_for_all_the_fields_without_authorization(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
		List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
	    userAddress.put("zipcode",data.get(int1).get("zipcode"));
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", userAddress);
	}

	@When("Send a post request to a valid endpoint without Authorization")
	public void send_a_post_request_to_a_valid_endpoint_without_authorization() {
		response = RestAssured.given().contentType(ContentType.JSON) 
	            .body(requestBody) 
	            .post(RestAssured.baseURI+"uap/createusers");
	}

	@When("User sends the GET request to valid endpoint without Authorization")
	public void user_sends_the_get_request_to_valid_endpoint_without_authorization() {
		response = RestAssured.given().when().get(RestAssured.baseURI+"uap/users");
	}

}
