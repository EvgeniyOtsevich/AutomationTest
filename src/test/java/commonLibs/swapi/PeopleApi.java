package commonLibs.swapi;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import pojo.PeopleResponse;

import static io.restassured.RestAssured.given;

public class PeopleApi extends BaseApi{

    @Step("Get people by ID = {id}")
    public PeopleResponse getPeopleById(int id){
        return given().spec(requestSpec)
                .when().get(PEOPLE, id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().as(PeopleResponse.class);
    }
}
