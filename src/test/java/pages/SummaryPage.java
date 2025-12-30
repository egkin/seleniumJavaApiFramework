package pages;

import org.openqa.selenium.By;

public class SummaryPage {

    // ================= LOCATORS =================
    public static class Locators {
        public static final By PAGE_TITLE = By.xpath("//span[@data-test='title']");
        public static final By SUBTOTAL_PRICE = By.xpath("//div[@data-test='subtotal-label']");
        public static final By FINISH_BUTTON = By.xpath("//button[@data-test='finish']");
        public static final By TOTAL_PRICE = By.xpath("//div[@data-test='total-label']");
    }

    // ================= STRINGS =================
    public static class Strings {
        public static final String PAGE_TITLE = "Checkout: Overview";
    }

    // ================= PAGE ACTIONS =================

    public static boolean isSummaryPageLoaded() {
        return Page.isElementVisible(Locators.PAGE_TITLE);
    }

    public static double getSubtotalPrice() {
        return Page.getPrice(Locators.SUBTOTAL_PRICE);
    }

    public static double getTotalPrice() {
        return Page.getPrice(Locators.TOTAL_PRICE);
    }

    public static void finishOrder() {
        Page.clickOn(Locators.FINISH_BUTTON);
        Page.waitUntilVisible(ThankYouPage.Locators.PAGE_TITLE);
    }
}