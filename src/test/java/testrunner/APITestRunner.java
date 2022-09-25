package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( features = "src/test/resources/Features/ApiFeatures.feature",
        glue = {"stepdefinitions"},
        tags= "@Test1",
        plugin = {"json:target/cucumber-reports/CucumberTestReport.json" ,
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failedrerun.txt"  })

public class APITestRunner extends AbstractTestNGCucumberTests {

}

