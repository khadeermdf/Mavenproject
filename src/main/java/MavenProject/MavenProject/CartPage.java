package MavenProject.MavenProject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import khadeer.reusableStuff.Abstractcode;

public class CartPage extends Abstractcode {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver); //
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".cart h3")
	List<WebElement> cartproducts;

	@FindBy(css = "li[class='totalRow'] button[type='button']")
	WebElement clickoncheckbutton;

	

	public boolean VerifyProductDisplay(String productName) {
		Boolean match = cartproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		return match;
		

	}

	public CheckOutPage goToCheckOutButton() {
		// TODO Auto-generated method stub
		clickoncheckbutton.click();
		return new CheckOutPage(driver);
	}

	
}
