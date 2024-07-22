package MavenProject.MavenProject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Project {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		String productname = "ADIDAS ORIGINAL";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		driver.get("https://rahulshettyacademy.com/client");

		// landingPage landingPage =new landingPage(driver);//this is witten form
		// landing page
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("khadeer@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Default@123");
		driver.findElement(By.xpath("//input[@id='login']")).click();

		// explicati wait is used to load and all products should be display
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		// select product to add to cart
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));// All products

		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productname)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		// Explict waits
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		// need to discuss with devloper about loading locators
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		// Add to cart
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartproducts = driver.findElements(By.cssSelector(".cart h3"));
		// below insteaded of filter we are any match becasse if math true or flse *
		// singular "cartproduct" is for check name in cart where we have multiple items
		Boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(true);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector(".ta-results.list-group.ng-star-inserted")));

		driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confimmessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confimmessage.equalsIgnoreCase("Thankyou for the order."));// equals ignaore case used for
																						// upper case text

		driver.close();

		//

	}

}
