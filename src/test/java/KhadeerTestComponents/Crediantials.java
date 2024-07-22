package KhadeerTestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import MavenProject.MavenProject.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Crediantials {
    public WebDriver driver;
    public   LandingPage landingPage;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\khadeercredentials\\Gobelfile.properties");
        
        prop.load(fis);
       String browserName =  System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");
       //String browserName = prop.getProperty("browser"); ///Instead of this above code is written to run from CMD 

        if (browserName.contains("chrome")) { //here it is changed from equalignorecase to contains to run the headless
        	ChromeOptions options =new ChromeOptions(); //this step is added to run headless mode
            WebDriverManager.chromedriver().setup();
            options.addArguments("headless");//this is also to run headless mode
            if (browserName.contains("headless")) {
            driver = new ChromeDriver(options);//here option is added to run in the headless mode
            driver.manage().window().setSize(new Dimension(1400,900)); //here also add this line of code
    }
        } else if (browserName.equalsIgnoreCase("firefox")) {
           //System.getProperty("Webdriver.gecko.driver","")
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {      
            driver = new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        driver.manage().window().maximize();
        
        return driver;
    }
	//This Hashmap is added in between for cread
	public List<HashMap<String, String>> getJsonData(String filename) throws IOException {
		
		//Read Json to String
		String JsonContent =FileUtils.readFileToString(new File(filename)
				,StandardCharsets.UTF_8);
			
	     //covert String to HashMap
		ObjectMapper Mapper = new ObjectMapper();
		
		//mapper is object
			List<HashMap<String, String>>data =Mapper.readValue(JsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		//  List<HashMap<String, String>>data =Mapper.readValue(JsonContent, new TypeReference<List<HasMap<String, String>>>(){});	
			return data;
				
	}

    @BeforeMethod
    public LandingPage launchApplication() throws IOException {
        driver = initializeDriver();
        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }
        @AfterMethod
        public void closeApplication() {
        	driver.close();
        }
        
    
}

