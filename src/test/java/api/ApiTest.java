package api;

import config.ApiTestData;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ApiTest {

    @Test
    void testUserName(){

        assertEquals(ApiTestData.user_name, ApiFunctions.getUserName(ApiTestData.userId));

    }

    @Test
    void testUserRole(){

        assertEquals(ApiTestData.user_role, ApiFunctions.getUserRole(ApiTestData.userId));

    }

    @Test
    void testUserAge(){

        assertEquals(ApiTestData.user_age, ApiFunctions.getUserAge(ApiTestData.userId));

    }

    @Test
    void testCreateUser(){

        Response response = ApiFunctions.createUser(ApiTestData.new_user_name, ApiTestData.new_user_role);

        assertEquals(201, response.statusCode());
        assertEquals(ApiTestData.new_user_name, response.jsonPath().getString("firstName"));
        assertEquals(ApiTestData.new_user_role, response.jsonPath().getString("role"));
    }
}
