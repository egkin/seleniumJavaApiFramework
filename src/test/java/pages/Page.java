package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Browser;

import java.time.Duration;
import java.util.List;

public class Page {

    private static WebDriver getDriver() {
        return Browser.getDriver();
    }

    private static WebDriverWait waitFor() {
        return new WebDriverWait(getDriver(), Duration.ofSeconds(10));
    }

    // ================= PAGE ACTIONS =================

    public static boolean isElementVisible(By locator) {
        try {
            waitFor().until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void waitUntilVisible(By locator) {
        waitFor().until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void clickOn(By locator) {
        waitUntilVisible(locator);
        getDriver().findElement(locator).click();
    }

    public static void inputText(By locator, String text) {
        waitUntilVisible(locator);
        WebElement field = getDriver().findElement(locator);
        field.clear();
        field.sendKeys(text);
    }

    public static String getText(By locator) {
        waitUntilVisible(locator);
        return getDriver().findElement(locator).getText();
    }

    public static List<WebElement> findElements(By locator) {
        return getDriver().findElements(locator);
    }

    public static int numberOfElements(By locator) {
        return findElements(locator).size();
    }

    public static double getNumbers(String priceText) {
        return Double.parseDouble(priceText.replaceAll("[^\\d.]+", "").trim());
    }

    public static double getPrice(By locator) {
        String priceText = getText(locator);
        return getNumbers(priceText);
    }
}