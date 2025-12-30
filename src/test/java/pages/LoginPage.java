package pages;

import api.ApiActions;
import org.openqa.selenium.By;
import utils.Browser;

public class LoginPage {

    // ================= LOCATORS =================
    public static class Locators {
        public static final By USERNAME_FIELD = By.id("user-name");
        public static final By PASSWORD_FIELD = By.id("password");
        public static final By LOGIN_BUTTON = By.id("login-button");
    }

    // ================= STRINGS =================
    public static class Strings {
        public static final String PAGE_TITLE = "Swag Labs";
    }

    // ================= PAGE ACTIONS =================

    public static boolean isPageLoaded() {
        return Browser.getDriver().getTitle().contains(Strings.PAGE_TITLE);
    }

    public static boolean isLoginFormVisible() {
        return Page.isElementVisible(Locators.USERNAME_FIELD) &&
                Page.isElementVisible(Locators.PASSWORD_FIELD) &&
                Page.isElementVisible(Locators.LOGIN_BUTTON);
    }

    public static void login(String username, String password) {
        Page.inputText(Locators.USERNAME_FIELD, username);
        Page.inputText(Locators.PASSWORD_FIELD, password);
        Page.clickOn(Locators.LOGIN_BUTTON);
    }

    public static void userLogin() {
        login(ApiActions.getUserLoginName(), ApiActions.getUserPassword());
        Page.waitUntilVisible(ProductPage.Locators.PAGE_TITLE);
    }
}