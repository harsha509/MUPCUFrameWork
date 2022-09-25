package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.shaded.gherkin.messages.internal.gherkin.internal.com.eclipsesource.json.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.asynchttpclient.Response;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import rest.Helper;
import io.restassured.path.json.JsonPath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class ApiTestSteps {
    public static RequestSpecification requestSpecBuilder;
    public static ResponseSpecification responseSpecBuilder;

    @Before
    public void hitAPI() {
        requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.zippopotam.us/").
                build();
    }

    @Before
    public void responseSpecifiction() {
        responseSpecBuilder = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                expectStatusCode(200).build();
    }

    @Given("Get the request")
    public void getRequest() {
        given().
                spec(requestSpecBuilder).
                when().
                get("AD/AD100").
                then().
                assertThat().
                body("places[0].'place name'", equalTo("Canillo"));
    }

    @Then("check Status Code is {string}")
    public void checkStatusCode(String statusCode) {
        given().
                spec(requestSpecBuilder).
                when().
                get("AD/AD100").
                then().
                assertThat().
                statusCode(Integer.parseInt(statusCode));
    }

    @Then("check the content Type is {string}")
    public void checkContentType(String contentType) {
        given().
                spec(requestSpecBuilder).
                when().
                get("AD/AD100").
                then().
                spec(responseSpecBuilder);
    }

    @Given("Print the request and reponse")
    public void logAllRequestAndResponse() {
        given().
                log().all().
                spec(requestSpecBuilder).
                when().
                get("AD/AD100").
                then().
                log().body();
    }

    @Then("Extract the place name and Veirfy if place name is {string}")
    public void checkItemFromList(String expectedName) {

        String placeName = given().
                spec(requestSpecBuilder).
                when().
                get("AD/AD100")
                .then().
                log().
                body().
                extract().
                body()
                //.asString();
                .path("places[0].'place name'");
       String token = JsonPath.from(placeName).getString("country");
       System.out.println("Country : "+ token);


       // Assert.assertEquals(expectedName, placeName);
    }

    @Then("Verify if the country is {string}")
    public void deserializeExample(String country) {
        Helper helper =
                given().
                        contentType(ContentType.JSON).
                        spec(requestSpecBuilder).
                        when().
                        get("AD/AD100").
                        as(Helper.class);
        Assert.assertEquals(country, helper.getCountry());
    }

    /**
     * Java object to JSON serialize
     */
    @Then("Change the country to {string}")
    public void serializeExample(String country) {
        Helper helper = new Helper();
        helper.setCountry(country);
        given().
                contentType(ContentType.JSON).
                spec(requestSpecBuilder).
                body("Andorra").
                log().body().
                when().
                post("AD/AD100").
                then().
                assertThat().
                statusCode(200);
    }

    @Given("Launch Go rest application")
    public void launch_go_rest_application() {
        given().when().get("https://gorest.co.in/public/v2/posts")
                .then().statusCode(200).log().body();
    }

    @Then("Verify the name is {string} and genger is {string}")
    public void verify_the_name_is_and_genger_is(String name, String gender) {
//        HashMap<String, String> data = new HashMap<String, String>();
//        data.put("title", "Atque voluptatem consequuntur verbum cito illum2");
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://gorest.co.in/public/v2").build();
       String actualName = given().
                spec(requestSpecification).
                when()
//                .body(data)
                .get("users/8")
                .then()
                .statusCode(200).extract().path("title");

        System.out.println("Actual Names" + actualName);
    }

    @Then("post a record into users")
    public void post_a_record_into_users() {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("name", "bodboyyharsha");
        data.put("job", "test");
        RequestSpecification req = new RequestSpecBuilder().
                setBaseUri("https://reqres.in/api").
                build();
        String apiBody = "{\"name\":\"value1\",\"job\":\"value2\",\"id\":\"value3\",\"createdAt\":\"value4\"}";

    String name =  given()
                .spec(req)
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("/users")
                .then()
                .statusCode(201).log().body()
                .extract().body().asString();
        System.out.println("name :" + name);
//        JsonPath jp = null;
//        jp.get();
    }
}
