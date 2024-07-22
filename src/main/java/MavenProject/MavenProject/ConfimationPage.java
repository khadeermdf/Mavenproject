package MavenProject.MavenProject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import khadeer.reusableStuff.Abstractcode;

public class ConfimationPage extends Abstractcode {
	WebDriver driver;

	public ConfimationPage(WebDriver driver) {
		super(driver); //
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css = ".hero-primary")
    WebElement confirmMessage;

    public String getConfirmMessage() {
         return confirmMessage.getText();
         
	}

}
