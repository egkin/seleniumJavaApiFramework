package pages;

import org.openqa.selenium.By;

public class ThankYouPage {

    // ================= LOCATORS =================
    public static class Locators {
        public static final By PAGE_TITLE = By.xpath("//span[@data-test='title']");
        public static final By SUCCESS_MESSAGE = By.xpath("//h2[@data-test='complete-header']");
    }

    // ================= STRINGS =================
    public static class Strings {
        public static final String SUCCESS_MESSAGE = "Thank you for your order!";
    }

    // ================= PAGE ACTIONS =================

    public static boolean isCheckoutCompleteTitleVisible() {
        return Page.isElementVisible(Locators.PAGE_TITLE);
    }

    public static boolean isSuccessMessageVisible() {
        return Page.isElementVisible(Locators.SUCCESS_MESSAGE);
    }

    public static String getSuccessMessageText() {
        return Page.getText(Locators.SUCCESS_MESSAGE);
    }
}