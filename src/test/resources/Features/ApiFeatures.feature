Feature: API

  Scenario: API get request
    Given  Get the request
    Then check Status Code is "200"
    Then check the content Type is "application/json"


  Scenario: API get request log and verify the data
    Given  Get the request
    Then Extract the place name and Veirfy if place name is "Canillo"

  Scenario: API get request and reponse logs
  Given Print the request and reponse

    Scenario:API get post code
      Given check Status Code is "200"
     # Then Verify if the country is "Andorra"
      Then Change the country to "US"