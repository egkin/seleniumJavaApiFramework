package api;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

public class ApiFunctions {

    public static Response getUser(int id) {
        return RestAssured.given().get(TestConfig.api_base_url + "/users/" + id);
    }

    public static String getUserName(int id) {
        return getUser(id).jsonPath().getString("firstName");
    }

    public static String getUserRole(int id) {
        return getUser(id).jsonPath().getString("role");
    }

    public static int getUserAge(int id) {
        return getUser(id).jsonPath().getInt("age");
    }

    public static Response createUser(String firstName, String role) {
        JSONObject body = new JSONObject();
        body.put("firstName", firstName);
        body.put("role", role);

        return RestAssured
                .given()
                .contentType("application/json")
                .body(body.toString())
                .post(TestConfig.api_base_url + "/users/add");
    }
}
