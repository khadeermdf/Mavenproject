package MavenProject.MavenProject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Errorvalidation {
	@Test
	public void submitOrder() {

		WebDriver driver = new ChromeDriver();

		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("khadeer@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Default@1223");
		driver.findElement(By.xpath("//input[@id='login']")).click();
	}

	@BeforeMethod
	public void submitOrder2() {
		WebDriverManager.chromedriver().setup();
		String productname = "ADIDAS ORIGINAL";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		driver.get("https://rahulshettyacademy.com/client");
	}

	@AfterMethod
	public void submitOrder3() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys("khadeer@gmail.com");
		driver.findElement(By.xpath("//input[@id='userPassword']")).sendKeys("Default@123");
		driver.findElement(By.xpath("//input[@id='login']")).click();
	}
}
