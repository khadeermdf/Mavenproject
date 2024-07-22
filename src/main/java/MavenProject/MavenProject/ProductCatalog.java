package MavenProject.MavenProject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import khadeer.reusableStuff.Abstractcode;

public class ProductCatalog extends Abstractcode {
	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;// it will treat as findelemts that we will add this
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement clickcart;
	
	
	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector(".ng-animating");

	public List<WebElement> getProductList() {
		waitForAbstractToApear(productBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
	return getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		
	
	}

	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForAbstractToApear(toastMessage);
		waitForElementToDisapper(spinner);
	

	}
	

	public CheckOutPage goToCheckOut() {
		clickcart.click();
		 return new CheckOutPage(driver);
	}

}
