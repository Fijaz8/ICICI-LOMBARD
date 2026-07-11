package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		tags = "@TS1 or @TS3",
        features = {
//        		"src/test/resources/features/TC11.feature", 
//        		"src/test/resources/features/TC12.feature", 
//        		"src/test/resources/features/TC13.feature",
//        		"src/test/resources/features/TC14.feature",
        		"src/test/resources/features"},
        glue     = {"stepDefinition", "Hooks"},

plugin = {
        "pretty",                                                                    
        "html:target/cucumber-report.html",                                          
        "json:target/cucumber.json",                                                 
        "junit:target/cucumber.xml",                                                 
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"       
    },

        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}