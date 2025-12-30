package pages;

import org.openqa.selenium.By;

public class BagpackProductDetailsPage {

    // ================= LOCATORS =================
    public static class Locators {
        public static final By BAGPACK_PRODUCT_TITLE = By.xpath(
                "//div[@data-test='inventory-item-name' and contains(text(), 'Sauce Labs Backpack')]");
        public static final By BACK_BUTTON = By.xpath("//button[@data-test='back-to-products']");
        public static final By PRODUCT_PRICE = By.xpath("//div[@data-test='inventory-item-price']");
        public static final By ADD_TO_CART_BUTTON = By.xpath("//button[@data-test='add-to-cart']");
        public static final By REMOVE_FROM_CART_BUTTON = By.xpath("//button[@data-test='remove']");
        public static final By SHOPPING_CART_ICON = By.xpath("//a[@data-test='shopping-cart-link']");
    }

    // ================= STRINGS =================
    public static class Strings {
        public static final String PRODUCT_TITLE = "Sauce Labs Backpack";
    }

    // ================= PAGE ACTIONS =================

    public static boolean isProductTitleVisible() {
        return Page.isElementVisible(Locators.BAGPACK_PRODUCT_TITLE);
    }

    public static boolean isBackButtonVisible() {
        return Page.isElementVisible(Locators.BACK_BUTTON);
    }

    public static double getProductPrice() {
        return Page.getPrice(Locators.PRODUCT_PRICE);
    }

    public static void addToCart() {
        Page.clickOn(Locators.ADD_TO_CART_BUTTON);
        Page.waitUntilVisible(Locators.REMOVE_FROM_CART_BUTTON);
    }

    public static boolean isRemoveButtonVisible() {
        return Page.isElementVisible(Locators.REMOVE_FROM_CART_BUTTON);
    }

    public static void goToShoppingCart() {
        Page.clickOn(Locators.SHOPPING_CART_ICON);
        Page.waitUntilVisible(ShoppingCartPage.Locators.PAGE_TITLE);
    }
}