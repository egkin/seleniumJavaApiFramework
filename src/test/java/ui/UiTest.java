package ui;

import config.Selectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import config.UiTestData;
import utils.WebDriver;

public class UiTest {

    @BeforeEach
    void startBrowser() {
        UiFunctions.openPage();
    }
    @AfterEach
    void closeBrowser() {
        UiFunctions.closeBrowser();
    }

    @Test
    void openMaximaSite() {
        assertEquals(UiTestData.title, UiFunctions.driver().getTitle());
    }

    @Test
    void testManoAciu() {
        UiFunctions.navigateTo(Selectors.mano_aciu);
        assertEquals(UiTestData.aciu_title, UiFunctions.getText(Selectors.aciu_tite));
    }

    @Test
    void testCorrectOfferDay() {
        int current_day = UiFunctions.getNumbers(Selectors.date).get(1);
        assertEquals(UiTestData.day, current_day);
    }

}
