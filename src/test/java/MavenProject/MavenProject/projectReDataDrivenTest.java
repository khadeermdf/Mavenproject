package MavenProject.MavenProject;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import KhadeerTestComponents.Crediantials;

public class projectReDataDrivenTest extends Crediantials {
	String productName = "ADIDAS ORIGINAL";

	@Test
	public void submitOrder() throws InterruptedException, IOException {

		LandingPage landingPage = launchApplication();
		landingPage.loginApplication("khadeer@gmail.com", "Default@123");
		ProductCatalog productCatalog = new ProductCatalog(driver);
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.getProductByName(productName);
		productCatalog.addProductToCart(productName);
		
		CartPage cartPage = new CartPage(driver);
		CartPage cartPage1 = productCatalog.goToCartPage();
		Boolean match = cartPage1.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		cartPage.goToCheckOutButton();
		CheckOutPage checkoutPage = new CheckOutPage(driver);
		checkoutPage.selectCountry("india");
		checkoutPage.submitOderButton();
		ConfimationPage confirmationPage = new ConfimationPage(driver);
		String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		driver.close();
	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {
		ProductCatalog productCatalog = landingPage.loginApplication("khadeer1@gmail.com", "Default@123");
		CartPage orderPage = productCatalog.goToCartPage();
		Assert.assertTrue(orderPage.VerifyProductDisplay(productName));
	}

}
