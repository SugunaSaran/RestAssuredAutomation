package stepdefinitions;

import static org.hamcrest.CoreMatchers.containsString;
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
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
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class PostRequestStep {
	Response response;
	ConfigFileReader configReader=new ConfigFileReader();
	private Map<String, Object> requestBody=new HashMap<>();
	private Map<String,Object> userAddress=new HashMap<>();
	private static String userid,userfirstname;
	/*private TextContext context;
	
	public PostRequestStep() {
		
	}
	public PostRequestStep(TextContext t) {
		this.context=t;
	}*/
	
	@Given("Give the request body with valid values {string} and {int} for all the fields")
	public void give_the_request_body_with_valid_values_and_for_all_the_fields(String string, Integer int1) throws InvalidFormatException, IOException {
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
        
	}

	@When("Send a post request to an invalid endpoint")
	public void send_a_post_request_to_an_invalid_endpoint() {
		response = RestAssured.given().header("Authorization", BackgroundStep.getAuthHeader()).contentType(ContentType.JSON) 
	            .body(requestBody) 
	            .post(RestAssured.baseURI+"uap/createu");
		}

	@Then("The response status code should be {int}")
	public void the_response_status_code_should_be_not_found(Integer int1) {
		assertEquals(int1, response.getStatusCode());
	}

	@Given("Give the request body with valid values {string} and {int} for all the fields and incorrect JSON format")
	public void give_the_request_body_with_valid_values_and_for_all_the_fields_and_incorrect_json_format(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
        userAddress.put("Street", data.get(int1).get("street"));
        userAddress.put("state", data.get(int1).get("state"));
        userAddress.put("Country", data.get(int1).get("country"));
        userAddress.put("zipCode", data.get(int1).get("zipcode"));
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", ",");
	}

	@When("Send a post request to a valid endpoint")
	public void send_a_post_request_to_a_valid_endpoint() {
		response = RestAssured.given().header("Authorization", BackgroundStep.getAuthHeader()).contentType(ContentType.JSON) 
	            .body(requestBody) 
	            .post(RestAssured.baseURI+"uap/createusers");
	}

	@Then("The response status code should be {int} and {string}")
	public void the_response_status_code_should_be_bad_request(Integer int1,String string) {
		 assertEquals(int1, response.getStatusCode());
		 response.then().body("message", containsString(string));
	}

	@Given("Give the request body with valid values {string} and {int} for only the mandatory fields")
	public void give_the_request_body_with_valid_values_and_for_only_the_mandatory_fields(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
	}

	@Then("The response status code should be {int}. A response body should have auto-generated user id, address id , creation_time and last_mod_time field.")
	public void the_response_status_code_should_be_a_response_body_should_have_auto_generated_user_id_address_id_creation_time_and_last_mod_time_field(Integer int1) {
		
		assertEquals(int1, response.getStatusCode());
		userid=response.jsonPath().getString("user_id");
		//System.out.println("userid in record creation: "+userid);
		userfirstname=response.jsonPath().getString("user_first_name");
		//System.out.println("userfirstname in record creation: "+userfirstname);
		String addressid=response.jsonPath().getString("userAddress.addressId");
		String creationTime=response.jsonPath().getString("creation_time");
		String modTime=response.jsonPath().getString("last_mod_time");
		Assert.assertNotNull(addressid);
		Assert.assertNotNull(creationTime);
		Assert.assertNotNull(modTime);
		
		
	}

	@Given("Give the request body with invalid value {string} and {int} for user_first_name field")
	public void give_the_request_body_with_invalid_value_and_for_user_first_name_field(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
        requestBody.put("user_first_name", data.get(int1).get("invalidfirstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", userAddress);
	}

	@Given("Give the request body {string} and {int} without first name field")
	public void give_the_request_body_and_without_first_name_field(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
        
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", userAddress);
	}

	@Given("Give the request body with first name field {string} and {int} as empty string")
	public void give_the_request_body_with_first_name_field_and_as_empty_string(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
       
        requestBody.put("user_first_name", " ");
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", userAddress);
	}

	@Then("The response status code should be {int}. The message is {string}")
	public void the_response_status_code_should_be_the_message_is(Integer int1, String string) {
		assertEquals(int1, response.getStatusCode());
		System.out.println("Status code from response: "+response.getStatusCode());
		//System.out.println("Status message from response: "+response.getBody().asString());
	}

	@Given("Give the request body with invalid value for user_last_name field {string} and {int}")
	public void give_the_request_body_with_invalid_value_for_user_last_name_field_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
       
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("invalidlastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", userAddress);
	}

	@Given("Give the request body without last name field {string} and {int}")
	public void give_the_request_body_without_last_name_field_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
       
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", userAddress);
	}

	@Given("Give the request body with last name field as empty string {string} and {int}")
	public void give_the_request_body_with_last_name_field_as_empty_string_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
        
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name"," ");
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", userAddress);
	}

	@Given("Give the request body with invalid value for contact number field {string} and {int}")
	public void give_the_request_body_with_invalid_value_for_contact_number_field_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
        
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("invalidcontactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", userAddress);
	}

	@Given("Give the request body without contact number field {string} and {int}")
	public void give_the_request_body_without_contact_number_field_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", userAddress);
	}

	@Given("Give the request body with contact number field as empty string {string} and {int}")
	public void give_the_request_body_with_contact_number_field_as_empty_string_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
        
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", " ");
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", userAddress);
	}

	@Given("Give the request body with duplicate contact number for user {int} and other fields like {string} and {int}")
	public void give_the_request_body_with_duplicate_contact_number_for_user_and_other_fields_like_and(Integer int1, String string, Integer int2) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("plotNumber",data.get(int2).get("plotNumber"));
        
        requestBody.put("user_first_name", data.get(int2).get("firstName"));
        requestBody.put("user_last_name",data.get(int2).get("lastName") );
        requestBody.put("user_contact_number", int1);
        requestBody.put("user_email_id", data.get(int2).get("email_id"));
        requestBody.put("userAddress", userAddress);
	}

	@Given("Give the request body with invalid value for email id field {string} and {int}")
	public void give_the_request_body_with_invalid_value_for_email_id_field_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
        
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("invalid_email_id"));
        requestBody.put("userAddress", userAddress);
	}

	@Given("Give the request body without email id field {string} and {int}")
	public void give_the_request_body_without_email_id_field_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
        
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("userAddress", userAddress);
	}

	@Given("Give the request body with email id field as empty string {string} and {int}")
	public void give_the_request_body_with_email_id_field_as_empty_string_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
       
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", " ");
        requestBody.put("userAddress", userAddress);
	}

	@Given("Give the request body with duplicate email id for user {string} and other fields like {string} and {int}")
	public void give_the_request_body_with_duplicate_email_id_for_user_and_other_fields_like_and(String string, String string2, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		userAddress.put("plotNumber",data.get(int1).get("plotNumber"));
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", string);
        requestBody.put("userAddress", userAddress);
        
	}
	
	
	
	@Given("Give the request body with all valid values for mandatory field and invalid value for plotnumber field like {string} and {int}")
	public void give_the_request_body_with_all_valid_values_for_mandatory_field_and_invalid_value_for_plotnumber_field_like_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("plotNumber",data.get(int1).get("invalidplotNumber"));
        
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", userAddress);
	}

	@Then("The response status code should be {int}. The message should be {string}")
	public void the_response_status_code_should_be_bad_request_the_message_should_be(Integer int1, String string) {
	    assertEquals(int1,response.getStatusCode());
	    assertEquals(string,response.jsonPath().getString("message"));
	}

	@Given("Give the request body with all valid values for mandatory field and invalid value for state field like {string} and {int}")
	public void give_the_request_body_with_all_valid_values_for_mandatory_field_and_invalid_value_for_state_field_like_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("state",data.get(int1).get("invalidstate"));
        
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", userAddress);
	}

	@Given("Give the request body with all valid values for mandatory field and invalid value for country field like {string} and {int}")
	public void give_the_request_body_with_all_valid_values_for_mandatory_field_and_invalid_value_for_country_field_like_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
		
        userAddress.put("country",data.get(int1).get("invalidcountry"));
       
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", userAddress);
	}

	

	@Given("Give the request body with all valid values for mandatory field and valid value for zipcode field like {string} and {int}")
	public void give_the_request_body_with_all_valid_values_for_mandatory_field_and_valid_value_for_zipcode_field_like_and(String string, Integer int1) throws InvalidFormatException, IOException {
		ExcelReader reader=new ExcelReader();
	    List<Map<String, String>> data = reader.getData(configReader.getExcelPath(), "PostRequestData");
	    userAddress.put("zipcode",data.get(int1).get("zipcode"));
        requestBody.put("user_first_name", data.get(int1).get("firstName"));
        requestBody.put("user_last_name",data.get(int1).get("lastName") );
        requestBody.put("user_contact_number", data.get(int1).get("contactNumber"));
        requestBody.put("user_email_id", data.get(int1).get("email_id"));
        requestBody.put("userAddress", userAddress);
       
	}
	public static String getUserID()
	{
		return userid;
	}
	public static String getUserFirstName() {
		return userfirstname;
	}
}
