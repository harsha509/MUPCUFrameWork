package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import rest.Helper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class ApiTestSteps {
    public static RequestSpecification requestSpecBuilder ;
    public static ResponseSpecification responseSpecBuilder ;
    @Before
    public void hitAPI(){
       requestSpecBuilder = new RequestSpecBuilder().
               setBaseUri("https://api.zippopotam.us").
               build();
    }
    @Before
    public void responseSpecifiction(){
        responseSpecBuilder = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                expectStatusCode(200).build();
    }

   @Given("Get the request")
    public void getRequest(){
        given().
                spec(requestSpecBuilder).
                when().
                get("AD/AD100").
                then().
                assertThat().
                body("places[0].'place name'",equalTo("Canillo"));
    }
    @Then("check Status Code is {string}")
    public void checkStatusCode(String statusCode){
        given().
                spec(requestSpecBuilder).
                when().
                get("AD/AD100").
                then().
                assertThat().
                statusCode(Integer.parseInt(statusCode));
    }
    @Then("check the content Type is {string}")
    public void checkContentType(String contentType){
        given().
                spec(requestSpecBuilder).
                when().
                get("AD/AD100").
                then().
                spec(responseSpecBuilder);
    }
    @Given("Print the request and reponse")
    public void logAllRequestAndResponse(){
        given().
                log().all().
                spec(requestSpecBuilder).
                when().
                get("AD/AD100").
                then().
                log().body();
    }
    @Then("Extract the place name and Veirfy if place name is {string}")
    public void checkItemFromList(String expectedName){

        String placeName = given().
                spec(requestSpecBuilder).
                when().
                get("AD/AD100")
                .then().extract().
                path("places[0].'place name'");
        Assert.assertEquals(expectedName,placeName);
    }
    @Then("Verify if the country is {string}")
    public void deserializeExample(String country){
        Helper  helper =
                given().
                        contentType(ContentType.JSON).
                        spec(requestSpecBuilder).
                        when().
                        get("AD/AD100").
                        as(Helper.class);
        Assert.assertEquals(country,helper.getCountry());
    }

    /**
     * Java object to JSON serialize
     *
     */
    @Then("Change the country to {string}")
    public void serializeExample(String country){
        Helper  helper = new Helper();
        helper.setCountry(country);
                given().
                        contentType(ContentType.JSON).
                        spec(requestSpecBuilder).
                        body(helper).
                         log().body().
                         when().
                        post("AD/AD100").
                        then().
                        assertThat().
                        statusCode(200);
    }

}
