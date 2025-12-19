package ui;

import config.Selectors;
import config.UiTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriver;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class UiFunctions {

    private static org.openqa.selenium.WebDriver driver;


    public static void openPage() {
        driver = WebDriver.browserStart();
        driver.get(UiTestData.url);
    }

    public static void closeBrowser() {
        WebDriver.quitDriver();
    }

    public static org.openqa.selenium.WebDriver driver() {
        if (driver == null) {
            driver = WebDriver.browserStart();
        }
        return driver;
    }

    public static void navigateTo(String selector) {
        driver.findElement(By.xpath(selector)).click();
    }

    public static String getText(String selector) {
        return driver.findElement(By.xpath(selector)).getText();
    }

    public static void waitToLoad(String selector) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(selector)));
        System.out.print(element.getText());
    }

    public static int numberOfOffers() {
        List<WebElement> offer_date = UiFunctions.driver().findElements(By.xpath(Selectors.offer_date));
        return offer_date.size();
    }

    public static List<Integer> getNumbers(String selector) {
        waitToLoad(selector);
        List<Integer> numbers = new ArrayList<>();
        WebElement element = driver.findElement(By.xpath(selector));
        String date_as_text = element.getText();
        String[] digits = date_as_text.split("\\D+");
        for (String digit : digits) {
            if (!digit.isEmpty()) {
                numbers.add(Integer.parseInt(digit));
            }
        }
        return numbers;
    }
}
