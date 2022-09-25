package stepdefinitions;

import com.pages.AccountsPage;
import com.pages.LoginPage;
import com.qa.factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountsPageSteps {
    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    AccountsPage accountsPage;
    private WebDriver driver;
    @Given("user has already logged in to application")
    public void user_has_already_logged_in_to_application(DataTable dataTable) {
        List<Map<String,String>> credList = dataTable.asMaps();
        String username= credList.get(0).get("username");
        String password= credList.get(0).get("password");
        DriverFactory.getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
        accountsPage= loginPage.doLogin(username,password);
       }
    @Then("user is on Accounts page")
    public void user_is_on_accounts_page() {
        accountsPage.getAccountsPageTitle();
        }
    @Then("user gets accounts section")
    public void user_gets_accounts_section(DataTable sectionTable) {
       List<String> expectedSectionList= sectionTable.asList();
        System.out.println("Expected accounts section List : "+ expectedSectionList);
        List<String> actualSectionList = accountsPage.getAccountsSectionsList();
        System.out.println("Actual accounts section List : "+ actualSectionList);
        Assert.assertEquals(expectedSectionList,actualSectionList);
    }
    @And("accounts section count should be {int}")
    public void user_gets_accounts_section(Integer count) {
      Assert.assertTrue(accountsPage.getAccountsSectionCount() ==  count );
    }

}
