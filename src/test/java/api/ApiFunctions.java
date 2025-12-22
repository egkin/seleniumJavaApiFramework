package api;

import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

public class ApiFunctions {

    /*JSON Dummy functions*/
    public static Response getUser(int id){
        return RestAssured.given()
                .get(TestConfig.jsdummy_base_url + "/users/" + id);
    }

    public static Response getUsers(){
        return RestAssured.given()
                .get(TestConfig.jsdummy_base_url + "/users/");
    }

    public static String getUserName(int id){
        return getUser(id).jsonPath()
                .getString("firstName");
    }

    public static String getUserRole(int id){
        return getUser(id).jsonPath().getString("role");
    }

    public static int getUserAge(int id){
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
                .post(TestConfig.jsdummy_base_url + "/users/add");
    }
    /*reqres functions require sending authorization in header*/
    public static Response getUsersReqres(){
        return RestAssured.given()
                .header("x-api-key", "reqres_c2a6a9fa1b974fbab45ae800dfd75803")
                .header("Content-Type", "application/json")
                .get(TestConfig.reqres_base_url+"/users");
    }

    public static Response createUserReqres(String name, String job){
        JSONObject body = new JSONObject();
        body.put("name", name);
        body.put("job", job);

        return RestAssured.given()
                .header("x-api-key", "reqres_c2a6a9fa1b974fbab45ae800dfd75803")
                .header("Content-Type", "application/json")
                .body(body.toString())
                .post(TestConfig.reqres_base_url+"/users");
    }

    public static Response updateReqresUserName(String name, String id){
        JSONObject body = new JSONObject();
        body.put("name", name);

        return RestAssured.given()
                .header("x-api-key", "reqres_c2a6a9fa1b974fbab45ae800dfd75803")
                .header("Content-Type", "application/json")
                .body(body.toString())
                .patch(TestConfig.reqres_base_url+"/users/"+id);
    }

    public static Response getLoginInformation() {
        return RestAssured
                .given()
                .when()
                .get(TestConfig.webhook_url)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    private static JsonPath getSanitizedJson() {
        String body = getLoginInformation().asString();

        // Sanitize smart quotes (Webhook / editor issues)
        body = body
                .replace("“", "\"")
                .replace("”", "\"");

        return new JsonPath(body);
    }

    public static String getUserLoginName() {
        return getSanitizedJson().getString("login");
    }

    public static String getUserPassword() {
        return getSanitizedJson().getString("password");
    }
}
