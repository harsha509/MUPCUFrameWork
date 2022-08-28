package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions( features = "@target/failedrerun.txt",
        glue = {"stepdefinitions","apphooks"},
        //tags = {"@First"} ,
        plugin = {"json:target/cucumber-reports/CucumberTestReport.json" ,
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class FailedRunner extends AbstractTestNGCucumberTests {

}
