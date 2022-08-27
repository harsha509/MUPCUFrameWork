Feature: Login page feature

  @First
  Scenario: Login page title
    Given user is on login page
    When user gets the title of the page
    Then page title should be "Login - My Store"

  Scenario: Login with correct credentials
    Given user is on login page
    When user enters username "testing199603@gmail.com"
    And user enters password "Selenium"
    And user clicks on Login button
    Then user gets the title of the page
    And page title should be "My account - My Store"