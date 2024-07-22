package MavenProject.MavenProject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import khadeer.reusableStuff.Abstractcode;

public class OrderPage extends Abstractcode {
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver); //
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css = "body app-root app-sidebar li:nth-child(3)")
	WebElement checkoutEle;

	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderproducts;
	
public  void ClickonorderButton() {
	checkoutEle.click();
}
	public boolean VerifyOrderDisplay(String productName) {
		Boolean match = orderproducts.stream()
				.anyMatch(cartproduct -> cartproduct.getText().equalsIgnoreCase(productName));
		return match;

	}

}
