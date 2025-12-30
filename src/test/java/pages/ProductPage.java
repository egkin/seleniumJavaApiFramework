package pages;

import org.openqa.selenium.By;

public class ProductPage {

    // ================= LOCATORS =================
    public static class Locators {
        public static final By PAGE_TITLE = By.xpath("//span[@class='title' and contains(text(),'Products')]");
        public static final By PRODUCTS = By.xpath("//div[@data-test='inventory-item']");
    }

    // ================= STRINGS =================
    public static class Strings {
        public static final String PAGE_TITLE = "Products";
    }

    // ================= PAGE ACTIONS =================

    public static boolean isProductsPageLoaded() {
        return Page.isElementVisible(Locators.PAGE_TITLE);
    }

    public static void openBagpackProductDetails() {
        Page.clickOn(BagpackProductDetailsPage.Locators.BAGPACK_PRODUCT_TITLE);
        Page.waitUntilVisible(BagpackProductDetailsPage.Locators.PRODUCT_PRICE);
    }

    public static int getVisibleProductCount() {
        return Page.numberOfElements(Locators.PRODUCTS);
    }

    public static boolean multipleProductsAreVisible() {
        return getVisibleProductCount() > 1;
    }
}