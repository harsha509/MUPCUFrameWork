package testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;


@CucumberOptions( features = "src/test/resources/Features",
            glue = {"stepdefinitions","apphooks"},
            plugin = {"json:target/cucumber-reports/CucumberTestReport.json" ,
            "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
            "rerun:target/failedrerun.txt"  })

    public class TestRunner extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider(parallel = false)
        public Object[][] scenarios()  {
            return super.scenarios();
        }
    }

