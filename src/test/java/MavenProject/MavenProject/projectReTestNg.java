package MavenProject.MavenProject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import KhadeerTestComponents.Crediantials;

public class projectReTestNg extends Crediantials {
	// String productName = "ADIDAS ORIGINAL"; this name is alreary given in anotherclass
	
	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String,String> input)throws InterruptedException, IOException {
		
	//public void submitOrder--(String email, String Password, String productName)
		//<--instead of this we will use HashMap "(HashMap<String,String> input)"
			//Two chrome drowser will open because before method is added
		LandingPage landingPage = launchApplication();
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("Password"));
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalog.goToCartPage();
		Thread.sleep(2000);
		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		 CheckOutPage checkoutPage = cartPage.goToCheckOutButton();
		checkoutPage.selectCountry("india");
		checkoutPage.scrolldown(500);
		
		ConfimationPage confirmationPage = checkoutPage.submitOderButton();
		String confirmMessage = confirmationPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
		
		
	}
     //,retryAnalyzer = RetryAnalyzer.class this is for retry faild test cases
	
	@Test(dependsOnMethods = { "submitOrder"},retryAnalyzer = RetryAnalyzer.class)
	public void orderHistoryTest() throws IOException {
		LandingPage landingPage = launchApplication();
		ProductCatalog productCatalog = landingPage.loginApplication("khadeer@gmail.com", "Default@123");
		CartPage orderPage = productCatalog.goToCartPage();
		Assert.assertTrue(orderPage.VerifyProductDisplay("productName"));
	}
	//This method is use for taking screen shots
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException { 
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	@DataProvider                      //this is dataprovider with the help of this we add multiple user or products
	public Object[][] getData() throws IOException {
		/*HashMap<String,String> map= new HashMap<String,String>();//HasHmap
		map.put("email", "khadeer@gmail.com");
		map.put("Password", "Default@123");
		map.put("productName", "ADIDAS ORIGINAL");
		HashMap<String,String> map1= new HashMap<String,String>();//HasHmap
		map1.put("email", "shetty@gamil.com");
		map1.put("Password", "Iamking@000");
		map1.put("productName", "zara coat 3");
		
		return new Object[][] { { map},
				{map1 } };*/
		List<HashMap<String, String>> data =getJsonData(System.getProperty("user.dir")+"\\src\\test\\java\\khadeerDemoJson\\data\\JsonData.json");
		return new Object[][] { {data.get(0)},{data.get(1)} };

	}
}