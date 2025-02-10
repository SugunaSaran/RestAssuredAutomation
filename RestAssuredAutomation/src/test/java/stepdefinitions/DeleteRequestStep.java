package stepdefinitions;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import DataProvider.ConfigFileReader;
import DataProvider.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteRequestStep {
	Response response;
	
	/*private TextContext context;
	
	public DeleteRequestStep() {
		
	}
	public DeleteRequestStep(TextContext t) {
		this.context=t;
	}*/
	ConfigFileReader configReader=new ConfigFileReader();
	private String userid,userFirstName;
	
	
	@Given("User Creates a DELETE request  with a valid User ID")
	public void user_creates_a_delete_request_with_a_valid_user_id() {
	  userid=PostRequestStep.getUserID();
	  System.out.println("user id in delete method: "+userid);
	}

	@When("User sends the DELETE request to valid endpoint with user ID")
	public void user_sends_the_delete_request_to_valid_endpoint_with_user_id() {
		response = RestAssured.given().header("Authorization", BackgroundStep.getAuthHeader()).pathParam("userID", userid).when().delete(RestAssured.baseURI+"uap/deleteuser/{userID}");
	}

	@Given("User Creates a DELETE request  with an empty User ID {string}.")
	public void user_creates_a_delete_request_with_an_empty_user_id(String string) {
	    userid=string;
	}

	@Given("User Creates a DELETE request  with an invalid userID in {string} and {int}")
	public void user_creates_a_delete_request_with_an_invalid_user_id_in_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "InvalidGetRequest");
	    userid=data.get(int1).get("Invalid User ID");
	}

	@Given("User Creates a DELETE request  with a valid User First Name as {string} and {int}")
	public void user_creates_a_delete_request_with_a_valid_user_first_name_as_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
	    userFirstName=data.get(int1).get("firstName");
	    System.out.println("user first name is "+userFirstName);
	}


	@When("User sends the DELETE request to valid endpoint with user firstname")
	public void user_sends_the_delete_request_to_valid_endpoint_with_user_firstname() {
		response = RestAssured.given().header("Authorization", BackgroundStep.getAuthHeader()).pathParam("userfirstname", userFirstName).when().delete(RestAssured.baseURI+"uap/deleteuser/username/{userfirstname}");
	}

	@Given("User Creates a DELETE request  with an empty User First Name as {string}.")
	public void user_creates_a_delete_request_with_an_empty_user_first_name_as(String string) {
	    userFirstName=string;
	}

	@Given("User Creates a DELETE request  with an invalid userFirstName in {string} and {int}")
	public void user_creates_a_delete_request_with_an_invalid_user_first_name_in_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "InvalidGetRequest");
	    userFirstName=data.get(int1).get("Invalid User Name");
	}
	@Then("The delete response should contain a status code as {int}.")
	public void the_delete_response_should_contain_a_status_code_as(Integer int1) {
	    assertEquals(int1,response.getStatusCode());
	}


}
