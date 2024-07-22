package khadeer.reusableStuff;



import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNg {

	public static ExtentReports getextentreports() { //here static is add to calll at class level
		
				String path = System.getProperty("user.dir")+"\\Reports\\indexreports.HTML";
				ExtentSparkReporter reporter = new ExtentSparkReporter(path);
				reporter.config().setReportName("khadeer extent test");
				reporter.config().setDocumentTitle("khadeer");	
				ExtentReports extent =new ExtentReports();
				extent.attachReporter(reporter);
				extent.setSystemInfo("Tester", "QA");	
				return extent;
				
			}
	}

