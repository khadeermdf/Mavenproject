package MavenProject.MavenProject;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import KhadeerTestComponents.Crediantials;

public class projectRe2 extends Crediantials {

    public void submitOrder() throws InterruptedException, IOException {
        String productName = "ADIDAS ORIGINAL";

      
        LandingPage landingPage = launchApplication();

        // Login to the application
        ProductCatalog productCatalog = landingPage.loginApplication("khadeer@gmail.com", "Default@123");

        // Get the product list and add the desired product to the cart
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(productName);

        // Navigate to the cart page
        CartPage cartPage = productCatalog.goToCartPage();

        // Verify the product is in the cart
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match, "Product not found in the cart");

        // Proceed to the checkout page
        CheckOutPage checkoutPage = cartPage.goToCheckOutButton();

        // Select the country and submit the order
        checkoutPage.selectCountry("india");
        ConfimationPage confirmationPage = checkoutPage.submitOderButton();

        // Verify the confirmation message
        String confirmMessage = confirmationPage.getConfirmMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."), "Order confirmation message does not match");
        driver.close();
    }
}
