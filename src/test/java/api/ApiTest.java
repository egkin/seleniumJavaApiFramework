package api;

import config.ApiTestData;
import config.TestConfig;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {

    @Test
    void testUserName() {
//        assertEquals(ApiTestData.user_name, ApiFunctions.getUserName(ApiTestData.userId));
        /*Both do the same below one instead of calling a method retrieves data directly from getUser() with id response*/
        Response response = ApiFunctions.getUser(ApiTestData.userId);
        response.then()
                .statusCode(describedAs("wrong status code, expected 200", equalTo(200)))
                .body("firstName", equalTo(ApiTestData.user_name));
    }

    @Test
    void testUserRole() {
//        assertEquals(ApiTestData.user_role, ApiFunctions.getUserRole(ApiTestData.userId));
        Response response = ApiFunctions.getUser(ApiTestData.userId);
        response.then()
                .body("role", equalTo(ApiTestData.user_role));
    }

    @Test
    void testUserAge() {
//        assertEquals(ApiTestData.user_age, ApiFunctions.getUserAge(ApiTestData.userId));
        Response response = ApiFunctions.getUser(ApiTestData.userId);
        response.then()
                .statusCode(200)
                .body("age", equalTo(ApiTestData.user_age));
    }

    @Test
    void testCreateUser() {
        Response response = ApiFunctions.createUser(ApiTestData.new_user_name, ApiTestData.new_user_role);

        assertEquals(201, response.statusCode());
        assertEquals(ApiTestData.new_user_name, response.jsonPath().getString("firstName"));
        assertEquals(ApiTestData.new_user_role, response.jsonPath().getString("role"));
    }

    @Test
    void testUserIsFound() {
        Response response = ApiFunctions.getUsers();

//        JsonPath jsonPath = response.jsonPath();
//        jsonPath.setRootPath("users");
//
//        List<Object> users = jsonPath
//                .param("firstName", ApiTestData.user_name)
//                .param("lastName", ApiTestData.user_last_name)
//                .getList("findAll { it.firstName == firstName && it.lastName == lastName }");
//
//        boolean userFound = !users.isEmpty();
//        assertTrue(userFound, "User '" + ApiTestData.user_name + " " + ApiTestData.user_last_name + " is not found!");
        response.then()
                .statusCode(200)
                .body("users.firstName", hasItem(ApiTestData.user_name))
                .body("users.lastName", hasItem(ApiTestData.user_last_name));
    }

    @Test
    void testUserCallReturn() {
        Response response = ApiFunctions.getUsersReqres();
        response.then()
                .statusCode(200);
    }

    @Test
    void testUserCreatedCallReturn() {
        Response response = ApiFunctions.createUserReqres(ApiTestData.new_user_name, ApiTestData.new_user_role);
       response.then()
               .statusCode(201);
    }

    @Test
    void testReqresResponseTime() {
        Response response = ApiFunctions.getUsersReqres();
        response.then()
                .time(lessThan(2000L));
    }

    @Test
    void userIsCreatedReqres() {
        Response response = ApiFunctions.createUserReqres(ApiTestData.new_user_name, ApiTestData.new_user_role);
        response.then()
                .body("name", describedAs("Name did not match", equalTo(ApiTestData.new_user_name)))
                .body("job", describedAs("Job title did not match", equalTo(ApiTestData.new_user_role)));
    }

    @Test
    void testUserFirstNameExists(){
        Response response = ApiFunctions.getUsersReqres();
        response.then()
                .body("data.first_name", hasItem(ApiTestData.reqres_user_first_name));
    }

    @Test
    void testUserExists(){
        Response response = ApiFunctions.getUsersReqres();
        response.then()
                .body("data.first_name", describedAs("User with name '"+ApiTestData.reqres_user_first_name+"' not found", hasItem(ApiTestData.reqres_user_first_name)))
                .body("data.last_name", describedAs("User with lastname '"+ApiTestData.reqres_user_last_name+"' not found", hasItem(ApiTestData.reqres_user_last_name)));
    }

    @Test
    void tetsUserLoginName(){
        assertEquals("standard_user",ApiFunctions.getUserLoginName());
    }

    @Test
    void debugWebhookResponse() {
        Response response = RestAssured
                .given()
                .get(TestConfig.webhook_url);

        System.out.println("STATUS: " + response.statusCode());
        System.out.println("CONTENT-TYPE: " + response.contentType());
        System.out.println("BODY:");
        System.out.println(response.asString());
    }
}
