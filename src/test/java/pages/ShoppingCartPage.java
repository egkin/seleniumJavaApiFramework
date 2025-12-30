package pages;

import org.openqa.selenium.By;

public class ShoppingCartPage {

    // ================= LOCATORS =================
    public static class Locators {
        public static final By CHECKOUT_BUTTON = By.xpath("//button[@data-test='checkout']");
        public static final By PAGE_TITLE = By.xpath("//span[@data-test='title' and text()='Your Cart']");
        public static final By CART_ITEMS = By.xpath("//div[@data-test='inventory-item']");
    }

    // ================= STRINGS =================
    public static class Strings {
        public static final String PAGE_TITLE = "Your Cart";
    }

    // ================= PAGE ACTIONS =================

    public static boolean isShoppingCartPageLoaded() {
        return Page.isElementVisible(Locators.PAGE_TITLE);
    }

    public static void proceedToCheckout() {
        Page.clickOn(Locators.CHECKOUT_BUTTON);
        Page.waitUntilVisible(CheckoutPage.Locators.PAGE_TITLE);
    }
}