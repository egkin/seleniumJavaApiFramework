package ui;

import config.Selectors;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import config.UiTestData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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
    void testMaximaSiteTitle() {
        assertEquals(UiTestData.title, UiFunctions.driver().getTitle());
    }

    @Test
    void testManoAciuTitle() {
        UiFunctions.navigateTo(Selectors.mano_aciu);
        assertEquals(UiTestData.aciu_title, UiFunctions.getText(Selectors.aciu_tite));
    }

    @Test
    void testCorrectOfferDay() {
        int current_day = UiFunctions.getNumbers(Selectors.offer_date).get(1);
        assertEquals(UiTestData.day, current_day);
    }

    @Test
    void testNumberOfOffers() {
        assertEquals(UiTestData.number_of_offers_on_page, UiFunctions.numberOfOffers(), "expected number of" +
                " offers"+" "+UiTestData.number_of_offers_on_page+" Actual found offers: "+UiFunctions.numberOfOffers());
    }

}
