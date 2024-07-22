package khadeerDemoJson.data;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import MavenProject.MavenProject.projectReTestNg;
import khadeer.reusableStuff.ExtentReporterNg;

public class Listeners extends projectReTestNg implements ITestListener {// first create cls in main then create class
																			// in test we have to implements ITestLister
																			// for extent report
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNg.getextentreports(); // Here Extentreport is return value which add in
																// extentreport file
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();//for concurrency we use this thread local

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);//it will set some Unique id
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test pass"); //heare also added extentTest.get() intead of test.log

	}

	@Override
	public void onTestFailure(ITestResult result) {
		// test.log(Status.FAIL, "Test fail"); //inteaded of this we can add below
		// script

		//test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable()); //instead of above code we are useing this is same concurrecy issue
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
																											
			// try catch on try catch click on the you will get all try catch remove all keep  1 as it is inside addd Exception																											// try
																													
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// After adding String filename =getScreenshot(----) try catch will display
		// click on that try catch will
		// display after that clickon string valibel
		// And also you need to add listerns in Xml file only system no about the
		// listeners
		String filename = null;
		try {
			filename = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filename, result.getMethod().getMethodName());
	}
	@Override
	public void onTestSkipped(ITestResult result) {
	}
	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	// Other methods from ITestListener interface that you can implement if needed

}
