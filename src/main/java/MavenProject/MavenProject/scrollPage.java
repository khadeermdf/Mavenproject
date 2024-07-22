package MavenProject.MavenProject;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class scrollPage {
    WebDriver driver;

    // Constructor to initialize WebDriver
    public scrollPage(WebDriver driver) {
        this.driver = driver;
    }

	public void scrolldown(int pixels) {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollBy(0, " + pixels + ")");
		
	}public void scrollup(int pixels) {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0, -" + pixels + ")");
}
}