package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features", // Path to your feature files
    glue = "stepdefinitions", 
    tags= "@NoAuth",// Path to your step definition classes
    plugin = {"pretty", "html:target/cucumber-report.html"} // Reporting plugins
)

public class NoAuthScenarios extends AbstractTestNGCucumberTests {

}
