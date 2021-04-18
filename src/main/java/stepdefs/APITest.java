package stepdefs;
import common.Headers;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.testng.asserts.SoftAssert;
import java.text.DecimalFormat;

import utilities.FileUtils;
import utilities.Logger;
import static common.Constants.*;


public class APITest {


    SoftAssert softAssert = new SoftAssert();


    @Given("^user invoke the api for cities$")
   public Response getCities(){
        Response response = Headers.GetHeader(CITY_URL);
        return response;
    }


    @When("^the user will get 200 response$")
    public void validate200(){
        softAssert.assertTrue(String.valueOf(getCities().getStatusCode()).equals("200"));
        softAssert.assertAll();
    }

    @Then("^user will see all the cities$")
    public void validateUserDetails(){
        softAssert.assertEquals(getCities().getBody().jsonPath().getList("id").size(), FileUtils.getCities().size(), "success message not displayed");
        for (int i=0;i<FileUtils.getCities().size();i++) {
            softAssert.assertEquals(getCities().getBody().jsonPath().get("id[" + i + "]"),FileUtils.getCities().get(i).getId(), "success message not displayed");
            softAssert.assertEquals(getCities().getBody().jsonPath().get("title["+i+"]"), FileUtils.getCities().get(i).title(), "success message not displayed");
            softAssert.assertAll();
        }

    }

    @Given("^user invoke the api for \\\"(.*)\\\"$")
    public  Response getAttractions(String city) {
        Response response = Headers.GetHeader(ATTRACTIONS_URL+city);
        return response;

    }

    @When("^the user will get 200 response for attractions \\\"(.*)\\\"$")
    public void validate200ForAttractions(String city){
        softAssert.assertTrue(String.valueOf(getAttractions(city).getStatusCode()).equals("200"));
        softAssert.assertAll();
    }


    @Then("^user will see all the attractions \\\"(.*)\\\"$")
    public void validateAttractionsDetails(String city) {
        softAssert.assertEquals(getAttractions(city).getBody().jsonPath().getList("id").size(), FileUtils.getAttractions().size(), "success message not displayed");
        softAssert.assertAll();
        for (int i = 0; i < FileUtils.getAttractions().size(); i++) {
            softAssert.assertEquals(getAttractions(city).getBody().jsonPath().get("id[" + i + "]"), FileUtils.getAttractions().get(i).getId(), "success message not displayed");
            softAssert.assertEquals(getAttractions(city).getBody().jsonPath().get("title[" + i + "]"), FileUtils.getAttractions().get(i).title(), "success message not displayed");
            softAssert.assertAll();
        }
    }

        @Given("^user invoke the api for desc\\\"(.*)\\\"&\\\"(.*)\\\"&\\\"(.*)\\\"&\\\"(.*)\\\"$")
        public  Response getAttractionsDesc(String city,String type,String sort,String order) {
            Response response = Headers.GetHeader(ATTRACTIONS_URL+city+"&type="+type+"&_sort="+sort+"&_order="+order);
            return response;

        }

    @When("^the user will get 200 response for attractions desc\\\"(.*)\\\"&\\\"(.*)\\\"&\\\"(.*)\\\"&\\\"(.*)\\\"$")
    public void validate200ForAttractionsDesc(String city,String type,String sort,String order){
        softAssert.assertTrue(String.valueOf(getAttractionsDesc(city,type,sort,order).getStatusCode()).equals("200"));
        softAssert.assertAll();
    }


    @Then("^user will see all the attractions desc\\\"(.*)\\\"&\\\"(.*)\\\"&\\\"(.*)\\\"&\\\"(.*)\\\"$")
    public void validateAttractionsDescDetails(String city,String type,String sort,String order) {
        softAssert.assertEquals(getAttractionsDesc(city,type,sort,order).getBody().jsonPath().getList("id").size(), FileUtils.getAttractionsDesc().size(), "success message not displayed");
        softAssert.assertAll();
        for (int i = 0; i < FileUtils.getAttractionsDesc().size(); i++) {
            DecimalFormat toTheFormat = new DecimalFormat("#.##");
            Logger.Log(getAttractionsDesc(city,type,sort,order).getBody().jsonPath().get("rating[" + i + "]"));
           double actualValue=Double.valueOf(toTheFormat.format(getAttractionsDesc(city,type,sort,order).getBody().jsonPath().get("rating[" + i + "]")));
           double expectedValue=Double.valueOf(FileUtils.getAttractionsDesc().get(i).rating());
            softAssert.assertEquals(actualValue,expectedValue , "success message not displayed");
            softAssert.assertEquals(getAttractionsDesc(city,type,sort,order).getBody().jsonPath().get("id[" + i + "]"), FileUtils.getAttractionsDesc().get(i).getId(), "success message not displayed");
            softAssert.assertEquals(getAttractionsDesc(city,type,sort,order).getBody().jsonPath().get("title[" + i + "]"), FileUtils.getAttractionsDesc().get(i).title(), "success message not displayed");
            softAssert.assertEquals(getAttractionsDesc(city,type,sort,order).getBody().jsonPath().get("type[" + i + "]"), FileUtils.getAttractionsDesc().get(i).type(), "success message not displayed");
            softAssert.assertEquals(getAttractionsDesc(city,type,sort,order).getBody().jsonPath().get("cityId[" + i + "]"), FileUtils.getAttractionsDesc().get(i).getCityId(), "success message not displayed");

            softAssert.assertAll();
        }
    }

}
