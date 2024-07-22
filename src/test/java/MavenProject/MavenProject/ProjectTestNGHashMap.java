package MavenProject.MavenProject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import KhadeerTestComponents.Crediantials;

public class ProjectTestNGHashMap extends Crediantials {

    @Test(dataProvider = "getData", groups = "Purchase")
    public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
        LandingPage landingPage = launchApplication();
        ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("Password"));
        List<WebElement> products = productCatalog.getProductList();
        productCatalog.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalog.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));

        Assert.assertTrue(match);
        CheckOutPage checkoutPage = cartPage.goToCheckOutButton();
        checkoutPage.selectCountry("india");
        ConfimationPage confirmationPage = checkoutPage.submitOderButton();
        String confirmMessage = confirmationPage.getConfirmMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."),
                "Order confirmation message does not match");
        driver.close();
    }

    @Test(dependsOnMethods = { "submitOrder" })
    public void orderHistoryTest() {
        ProductCatalog productCatalog = landingPage.loginApplication("khadeer@gmail.com", "Default@123");
        CartPage orderPage = productCatalog.goToCartPage();
        Assert.assertTrue(orderPage.VerifyProductDisplay("ADIDAS ORIGINAL"));
    }

    @DataProvider
    public Object[][] getData() {
        HashMap<String,String> map = new HashMap<String,String>();
        map.put("email", "khadeer@gmail.com");
        map.put("Password", "Default@123");
        map.put("productName", "ADIDAS ORIGINAL");

        HashMap<String,String> map1 = new HashMap<String,String>();
        map1.put("email", "khadeer1@gmail.com");
        map1.put("Password", "Default@123");
        map1.put("productName", "zara coat 3");

        return new Object[][] { { map }, { map1 } };
    }
}
