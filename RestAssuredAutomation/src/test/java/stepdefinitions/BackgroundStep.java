package stepdefinitions;

import DataProvider.ConfigFileReader;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;

import java.util.Base64;
public class BackgroundStep {
	ConfigFileReader configReader=new ConfigFileReader();
	private static String encodedAuthValue;
	
	@Given("User sets Basic Auth as Authorization for request")
	public void user_sets_basic_auth_as_authorization_to_get_token() {
		RestAssured.baseURI = configReader.getApplicationUrl();
		String authValue = configReader.getUserName() + ":" + configReader.getPassword();
        encodedAuthValue = new String(Base64.getEncoder().encode(authValue.getBytes()));
          
		
	}
	public static String getAuthHeader() {
		return "Basic "+encodedAuthValue;
	}
	
	@Given("User sets No Authorization")
	public void user_sets_no_authorization() {
		RestAssured.baseURI = configReader.getApplicationUrl();
	}


}
