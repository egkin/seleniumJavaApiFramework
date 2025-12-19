package api;

import config.ApiTestData;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ApiTest {

    @Test
    void testUserName() {
        assertEquals(ApiTestData.user_name, ApiFunctions.getUserName(ApiTestData.userId));
    }

    @Test
    void testUserRole() {
        assertEquals(ApiTestData.user_role, ApiFunctions.getUserRole(ApiTestData.userId));
    }

    @Test
    void testUserAge() {
        assertEquals(ApiTestData.user_age, ApiFunctions.getUserAge(ApiTestData.userId));
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

        JsonPath jsonPath = response.jsonPath();
        jsonPath.setRootPath("users");

        List<Object> users = jsonPath
                .param("firstName", ApiTestData.user_name)
                .param("lastName", ApiTestData.user_last_name)
                .getList("findAll { it.firstName == firstName && it.lastName == lastName }");

        boolean userFound = users.size() > 0;
        assertTrue(userFound, "User '"+ApiTestData.user_name+" "+ApiTestData.user_last_name+" is not found!");
    }
}
