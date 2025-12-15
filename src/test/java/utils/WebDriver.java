package utils;

import config.TestConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class WebDriver {

    private static org.openqa.selenium.WebDriver driver;

    public static org.openqa.selenium.WebDriver browserStart() {
        if (driver == null) {
            WebDriverManager.chromedriver()
                    .browserVersion(TestConfig.chrome_version)
                    .setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts()
                    .implicitlyWait(Duration.ofSeconds(TestConfig.wait_duration));
        }
        return driver;
    }

    public static void quitDriver() {
            if(driver != null) {
                driver.quit();
                driver = null;
            }

    }
}
