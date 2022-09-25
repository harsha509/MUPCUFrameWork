package stepdefinitions;

import com.pages.LoginPage;
import com.qa.factory.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginPageSteps {
    String title;
    LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    @Given("user is on login page")
    public void user_is_on_login_page() {
        DriverFactory.getDriver().get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }
    @When("user gets the title of the page")
    public void user_gets_the_title_of_the_page() {
        title= loginPage.getLoginPageTitle();
    }
    @Then("page title should be {string}")
    public void page_title_should_be(String string) {
        Assert.assertTrue(title.contains(string));
    }
    @Then("user enters username {string}")
    public void user_enters_username(String Username) {
        loginPage.enterUserName(Username);
    }
    @When("user enters password {string}")
    public void user_enters_password(String password) {
        loginPage.enterPassword(password);
    }
    @When("user clicks on Login button")
    public void user_clicks_on_login_button() {
        loginPage.clickOnLogin();
    }
}
