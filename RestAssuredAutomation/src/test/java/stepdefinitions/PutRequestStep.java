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

public class PutRequestStep {
	Response response;
	ConfigFileReader configReader=new ConfigFileReader();
	private Map<String, Object> requestBody=new HashMap<>();
	private Map<String,Object> userAddress=new HashMap<>();
	private String userid;

	@Given("Give the updated request body with valid values for all fields {string} and {int} with a  valid user id")
	public void give_the_updated_request_body_with_valid_values_for_all_fields_and_with_a_valid_user_id(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
		List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
		userAddress.put("Street", data.get(int1).get("street"));
		userAddress.put("state", data.get(int1).get("state"));
		userAddress.put("Country", data.get(int1).get("country"));
		userAddress.put("zipCode", data.get(int1).get("zipcode"));

		requestBody.put("user_first_name", data.get(int1).get("firstName"));
		requestBody.put("user_last_name",data.get(int1).get("lastName") );
		requestBody.put("user_contact_number",data.get(int1).get("contactNumber"));
		requestBody.put("user_email_id", data.get(int1).get("email_id"));
		requestBody.put("userAddress", userAddress);
		userid=PostRequestStep.getUserID();
	}

	@When("Send a put request to a valid endpoint")
	public void send_a_put_request_to_a_valid_endpoint() {
		response = RestAssured.given().header("Authorization", BackgroundStep.getAuthHeader()).pathParam("userID", userid).contentType(ContentType.JSON) 
				.body(requestBody) 
				.put(RestAssured.baseURI+"uap/updateuser/{userID}");
		//System.out.println("Message is:"+response.jsonPath().getString("message"));
	}

	@Given("Give the updated request body with duplicate contact number for user {int} and other fields like {string} and {int}")
	public void give_the_updated_request_body_with_duplicate_contact_number_for_user_and_other_fields_like_and(Integer int1, String string, Integer int2) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
		List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");

		userAddress.put("plotNumber",data.get(int2).get("plotNumber"));

		requestBody.put("user_first_name", data.get(int2).get("firstName"));
		requestBody.put("user_last_name",data.get(int2).get("lastName") );
		requestBody.put("user_contact_number", int1);
		requestBody.put("user_email_id", data.get(int2).get("email_id"));
		requestBody.put("userAddress", userAddress);
		userid="1";
	}

	@Given("Give the updated request body with invalid value for email id field {string} and {int}")
	public void give_the_updated_request_body_with_invalid_value_for_email_id_field_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
		List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
		requestBody.put("user_first_name", data.get(int1).get("firstName"));
		requestBody.put("user_last_name",data.get(int1).get("lastName") );
		requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
		requestBody.put("user_email_id", data.get(int1).get("invalid_email_id"));
		requestBody.put("userAddress", userAddress);
		userid="1";
	}

	@Then("The response status code should be {int} and the response body should have auto-generated user id, address id , creation_time and last_mod_time field.")
	public void the_response_status_code_should_be_and_the_response_body_should_have_auto_generated_user_id_address_id_creation_time_and_last_mod_time_field(Integer int1) {
		assertEquals(response.getStatusCode(),int1);
	}

	@Then("The response status code should be {int} for update. The message is {string}")
	public void the_response_status_code_should_be_for_update_the_message_is(Integer int1, String string) {
		assertEquals(int1,response.getStatusCode());
		assertEquals(string,response.jsonPath().getString("message"));
	}

	@Then("The response status code should be {int} for update.")
	public void the_response_status_code_should_be_for_update(Integer int1) {
		assertEquals(response.getStatusCode(),int1);
	}
	@Given("Update the request body with duplicate email id for user {string} and other fields like {string} and {int}")
	public void update_the_request_body_with_duplicate_email_id_for_user_and_other_fields_like_and(String string, String string2, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
		List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
		userAddress.put("Street", data.get(int1).get("street"));
		userAddress.put("state", data.get(int1).get("state"));
		userAddress.put("Country", data.get(int1).get("country"));
		userAddress.put("zipCode", data.get(int1).get("zipcode"));
		requestBody.put("user_first_name", data.get(int1).get("firstName"));
		requestBody.put("user_last_name",data.get(int1).get("lastName"));
		requestBody.put("user_contact_number",data.get(int1).get("contactNumber"));
		requestBody.put("user_email_id", string);
		requestBody.put("userAddress", userAddress);
		userid="1";
	}




}
