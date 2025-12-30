package pages;

import org.openqa.selenium.By;

public class CheckoutPage {

    // ================= LOCATORS =================
    public static class Locators {
        public static final By PAGE_TITLE = By.xpath("//span[@data-test='title']");
        public static final By FIRST_NAME_INPUT_FIELD = By.xpath("//input[@data-test='firstName']");
        public static final By LAST_NAME_INPUT_FIELD = By.xpath("//input[@data-test='lastName']");
        public static final By POSTAL_CODE_INPUT_FIELD = By.xpath("//input[@data-test='postalCode']");
        public static final By CONTINUE_BUTTON = By.xpath("//input[@data-test='continue']");
    }

    // ================= STRINGS =================

    // ================= PAGE ACTIONS =================

    public static boolean isCheckoutPageLoaded() {
        return Page.isElementVisible(Locators.PAGE_TITLE);
    }

    public static void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        Page.inputText(Locators.FIRST_NAME_INPUT_FIELD, firstName);
        Page.inputText(Locators.LAST_NAME_INPUT_FIELD, lastName);
        Page.inputText(Locators.POSTAL_CODE_INPUT_FIELD, postalCode);
    }

    public static void continueToOverview() {
        Page.clickOn(Locators.CONTINUE_BUTTON);
        Page.waitUntilVisible(SummaryPage.Locators.PAGE_TITLE);
    }
}