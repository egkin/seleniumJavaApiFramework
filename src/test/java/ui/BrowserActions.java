package ui;

import config.TestConfig;
import utils.Browser;

public class BrowserActions {

    public static void openPage() {
        Browser.getDriver().get(TestConfig.starting_page);
    }

    public static String getCurrentUrl() {
        return Browser.getDriver().getCurrentUrl();
    }

    public static String getPageTitle() {
        return Browser.getDriver().getTitle();
    }
}