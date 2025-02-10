package runners;

import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features", // Path to your feature files
	    glue = "stepdefinitions", 
	    tags= "@UserCreation",// Path to your step definition classes
	    plugin = {"pretty", "html:target/cucumber-report.html"} // Reporting plugins
	)

	public class UserCreationRunner extends AbstractTestNGCucumberTests {
}
