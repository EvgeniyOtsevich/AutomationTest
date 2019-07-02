package commonLibs.maxpay;

import commonLibs.ui.user.User;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public final class MaxPayApi {

    @Step("Create New user throws API")
    public static boolean createUser(User user){
        return given()
                .baseUri("https://my-sandbox.maxpay.com").basePath("/api.php")
                .header("x-requested-with", "XMLHttpRequest")
                .contentType(ContentType.JSON)
                .body("{\"email\":\""+user.getEmail()+"\",\"password\":\""+user.getPassword()+"\",\"confirm\":\""+user.getPassword()+"\",\"agreeTos\":true,\"testMode\":false,\"action\":\"signup\",\"jstpx\":{\"deviceId\":\"a1886a8d0dd7a1564523ebee2908b78f\",\"producedIn\":0.06,\"online\":36.966,\"previous\":\"https://my-sandbox.maxpay.com/app.php\",\"userAgent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36\"},\"userAgent\":\"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36\",\"machineId\":\"a1886a8d0dd7a1564523ebee2908b78f\",\"screen\":\"1080x1920\"}")
                .when().post()
                .then()
                .extract().response().path("success").equals(true);
    }
}
