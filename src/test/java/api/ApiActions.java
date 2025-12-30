package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class ApiActions {

    public static Response getLoginInformation() {
        return given()
                .when()
                .get(ApiEndpoints.LOGIN_INFORMATION)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    public static String getUserLoginName() {
        return getLoginInformation().jsonPath().getString("login");
    }

    public static String getUserPassword() {
        return getLoginInformation().jsonPath().getString("password");
    }

    public static Response updateTotalPrice(double total_price) {
        JSONObject body = new JSONObject();
        body.put("total", total_price);
        return given()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .when()
                .post(ApiEndpoints.PRICE_UPDATE);
    }
}
