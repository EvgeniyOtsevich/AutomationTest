package tests.API;

import commonLibs.swapi.FilmsApi;
import commonLibs.swapi.PeopleApi;
import commonLibs.swapi.PlanetsApi;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pojo.FilmResponse;
import pojo.PeopleResponse;
import pojo.PlanetsResponse;

import static org.assertj.core.api.Assertions.assertThat;

public class SwapiTest {

    private SoftAssertions softAssertions = new SoftAssertions();

    private PeopleApi peopleApi = new PeopleApi();
    private PlanetsApi planetsApi = new PlanetsApi();
    private FilmsApi filmsApi = new FilmsApi();

    private PeopleResponse peopleResponse;
    private PlanetsResponse planetsResponse;
    private FilmResponse filmResponse;


    @Parameters({"id","name"})
    @Test(description = "Find people by ID and Verify Name")
    public void findPeopleByIdTest(int id, String name){
        peopleResponse = peopleApi.getPeopleById(id);
        assertThat(peopleResponse.getName()).isEqualTo(name);
    }

    @Parameters({"planet_name","population"})
    @Test(dependsOnMethods = "findPeopleByIdTest", description = "Find Planet by People's Home Work and Verify Planet/Population")
    public void findPlanetByPeopleHomeWorldTest(String planetName, String population){
        planetsResponse = planetsApi.getPlanetByPeopleHomeWorld(peopleResponse);
        softAssertions.assertThat(planetsResponse.getName()).isEqualTo("Tatooine");
        softAssertions.assertThat(planetsResponse.getPopulation()).isEqualTo("200000");
        softAssertions.assertAll();
    }

    @Parameters({"film"})
    @Test(dependsOnMethods = "findPlanetByPeopleHomeWorldTest", description = "Check Film Title and Verify Name/Planet belong to the film")
    public void checkTitleCharacterPlanetForFirstFilm(String film){
        filmResponse = filmsApi.getFirstFilmFromPlanet(planetsResponse);
        softAssertions.assertThat(filmResponse.getTitle()).isEqualTo(film);
        softAssertions.assertThat(filmResponse.getPlanets()).contains(planetsResponse.getUrl());
        softAssertions.assertThat(filmResponse.getCharacters()).contains(peopleResponse.getUrl());
        softAssertions.assertAll();
    }
}
