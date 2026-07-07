package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features",
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