package MavenProject.MavenProject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import khadeer.reusableStuff.Abstractcode;

public class CheckOutPage extends Abstractcode {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver); //
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement selectCountry;

	@FindBy(css = ".btnn.action__submit.ng-star-inserted")
	WebElement submit;

	By results = By.cssSelector(".ta-results");

	public void selectCountry(String countryName) throws InterruptedException {

		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		Thread.sleep(3000);
		waitForAbstractToApear(By.cssSelector(".ta-results"));
		Thread.sleep(3000);
		selectCountry.click();
	}

	public ConfimationPage submitOderButton() throws InterruptedException {
		
          submit.click();
          Thread.sleep(2000);
		
		return new ConfimationPage(driver);
	}

		public void scrolldown(int pixels) {
			 JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("window.scrollBy(0, " + pixels + ")");
		        
		// TODO Auto-generated method stub
		
	}
}
