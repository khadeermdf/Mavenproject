package MavenProject.MavenProject;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import khadeer.reusableStuff.Abstractcode;

public class LandingPage extends Abstractcode{
	WebDriver driver;

	
	public LandingPage(WebDriver driver) {// this is called as instructor after send object in main and we have to tell to constructor
        super(driver); //
		this.driver = driver;                     // this driver have only inside this 1st step add this step and create object tfor this landing in main project
		PageFactory.initElements(driver, this);   //if page factory is used we can use @FindBy
	}

	    // WebElement userEmails=driver.findElement(By.xpath("//input[@id='userEmail']")); instead of this we write below code
	
	    @FindBy(xpath="//input[@id='userEmail']")
         WebElement userEmail;
         
         @FindBy(xpath="//input[@id='userPassword']")
         WebElement passWordEle;
         
         @FindBy(xpath="//input[@id='login']")
         WebElement submitbutton;

           public void goTo() {
	        driver.get("https://rahulshettyacademy.com/client");
	        System.out.println(driver.getTitle());
	}
         public ProductCatalog loginApplication(String email, String password) {
        	 userEmail.sendKeys(email);
        	 passWordEle.sendKeys(password);
        	 submitbutton.click();
        	 return new ProductCatalog(driver);
       
        	
        }   
}
