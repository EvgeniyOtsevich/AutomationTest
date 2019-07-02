package commonLibs.swapi;

import io.qameta.allure.Step;
import org.apache.http.HttpStatus;
import pojo.FilmResponse;
import pojo.PlanetsResponse;

import static io.restassured.RestAssured.given;

public class FilmsApi extends BaseApi {

    @Step("Get First Film from Planet")
    public FilmResponse getFirstFilmFromPlanet(PlanetsResponse planet){
        return given()
                .when().get(planet.getFilms()[0])
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().as(FilmResponse.class);
    }
}
