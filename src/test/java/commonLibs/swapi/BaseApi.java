package commonLibs.swapi;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

abstract class BaseApi {

    private final String BASE_URL = "https://swapi.co/api";

    final String PEOPLE = "/people/{id}";

    final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri(BASE_URL)
            .setAccept(ContentType.JSON)
            .build();

}
