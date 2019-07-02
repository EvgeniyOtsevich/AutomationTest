package commonLibs.swapi;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import pojo.PeopleResponse;
import pojo.PlanetsResponse;

import static io.restassured.RestAssured.given;

public class PlanetsApi extends BaseApi {

    @Step("Get Planet by People Home World")
    public PlanetsResponse getPlanetByPeopleHomeWorld(PeopleResponse people){
        return given()
                .when().get(people.getHomeworld())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().as(PlanetsResponse.class);
    }
}
