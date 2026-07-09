package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {
//        		"src/test/resources/features/TC11.feature", 
//        		"src/test/resources/features/TC12.feature", 
//        		"src/test/resources/features/TC13.feature",
//        		"src/test/resources/features/TC14.feature",
        		"src/test/resources/features/TC17.feature"},
        glue     = {"stepDefinition", "Hooks"},
        plugin   = {
                    "pretty",
                    "html:target/report.html",
                    "json:target/report.json"
                   },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}