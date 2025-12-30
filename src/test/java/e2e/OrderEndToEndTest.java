package e2e;

import api.ApiActions;
import config.TestConfig;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.ThankYouPage;
import ui.TestData;
import utils.Browser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static pages.LoginPage.*;
import static pages.ProductPage.*;
import static pages.BagpackProductDetailsPage.*;
import static pages.ShoppingCartPage.*;
import static pages.CheckoutPage.*;
import static pages.SummaryPage.*;
import static pages.ThankYouPage.*;

public class OrderEndToEndTest {

    @BeforeEach
    void setUp() {
        Browser.initializeDriver();
        Browser.getDriver().get(TestConfig.starting_page);
    }

    @AfterEach
    void tearDown() {
        Browser.closeDriver();
    }

    @Test
    void testUserProductOrderFlow() {
        // Step 1: Login
        assertTrue(isPageLoaded(), "Expected 'Login' page was not loaded");
        assertTrue(isLoginFormVisible(), "Expected 'Login form' was not visible");

        userLogin();

        // Step 2: Verify products page
        assertTrue(isProductsPageLoaded(), "Expected 'Product' page was not loaded");
        assertTrue(multipleProductsAreVisible(), "Expected multiple products, but were less");

        // Step 3: Open product details
        openBagpackProductDetails();

        assertTrue(isProductTitleVisible() && isBackButtonVisible(),
                "'Product detail' page was not loaded");

        // Step 4: Add to cart
        double bagpack_listing_price = getProductPrice();
        addToCart();

        assertTrue(isRemoveButtonVisible(),
                "Add to cart button was not replaced by Remove from cart button");

        // Step 5: Go to cart and checkout
        goToShoppingCart();

        assertTrue(isShoppingCartPageLoaded(), "Expected 'Shopping cart' page was not loaded");

        proceedToCheckout();

        // Step 6: Fill checkout information
        assertTrue(isCheckoutPageLoaded(), "Expected 'Checkout' page was not loaded");

        enterCheckoutInformation(
                TestData.TEST_USER_FIRST_NAME,
                TestData.TEST_USER_LAST_NAME,
                TestData.TEST_POSTAL_CODE
        );

        continueToOverview();

        // Step 7: Verify summary
        assertTrue(isSummaryPageLoaded(), "Expected 'Summary' page did not load");

        double bagpack_final_price = getSubtotalPrice();
        assertEquals(bagpack_listing_price, bagpack_final_price,
                "Prices did not match, expected price: " + bagpack_listing_price +
                        "$ but was: " + bagpack_final_price + "$");

        // Step 8: Update total via API
        double total_price = getTotalPrice();
        assertEquals(201, ApiActions.updateTotalPrice(total_price).getStatusCode(),
                "API call to update total price failed");

        // Step 9: Finish order
        finishOrder();

        assertTrue(isCheckoutCompleteTitleVisible() && isSuccessMessageVisible(),
                "The final 'Thank You' page was not loaded");

        String actual_message_text = getSuccessMessageText();
        assertEquals(ThankYouPage.Strings.SUCCESS_MESSAGE, actual_message_text,
                "Message text '" + actual_message_text + "' did not match the expected message text '"
                        + ThankYouPage.Strings.SUCCESS_MESSAGE + "'");
    }
}