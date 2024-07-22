package khadeer.reusableStuff;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MavenProject.MavenProject.CartPage;
import MavenProject.MavenProject.OrderPage;

public class Abstractcode {
	WebDriver driver ;
	public Abstractcode(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	 @FindBy(css="[routerlink*='cart']")
     WebElement cartHeader;
	 @FindBy(css="[routerlink='/dashboard/myorders']")
     WebElement orderhistory;

	public void waitForAbstractToApear(By findBy){
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	 wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
		
}public void waitForElementToDisapper(WebElement ele) {
	WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(3));
	wait.until(ExpectedConditions.invisibilityOf(ele));
}
public OrderPage orderHistory() {
	orderhistory.click();
	OrderPage orderPage =new OrderPage(driver);
	return orderPage;
}
	}
