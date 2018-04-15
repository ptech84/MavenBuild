package com.freecrm.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.freecrm.Pages.HomePage;
import com.freecrm.Pages.LoginPage;
import com.freecrm.TestBase.TestBase;
import com.freecrm.Utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	ExtentReports extent;
	ExtentTest extentTest;
	Logger log = Logger.getLogger(LoginTest.class);

	
	
	public LoginTest(){
		super();
	}
	
	@BeforeTest
	public void StartExtent(){
		extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/ExtentReport.html", true);
		extent.addSystemInfo("Host Name", "Praveen Microsoft");
		extent.addSystemInfo("User Name", "Praveen Automation Labs");
		extent.addSystemInfo("Environment", "QA");
	}
	
	@AfterTest
	public void endReport(){
		extent.flush();
		extent.close();
	
	}
	
	@BeforeMethod
	public void Setup(){
		log.info("**********Launching browser and loading URL**************");
		Initialization();
		loginPage = new LoginPage();
	}
	
	@Test(invocationCount=3, groups="login")
	public void Login() throws InterruptedException{
		extentTest = extent.startTest("Login");
		log.info("******************Login successfully to FREECRM application***************************");
	homePage = loginPage.LoginToFreeCRM(prop.getProperty("username"), prop.getProperty("password"));
	Thread.sleep(5000);
	}
	
	@Test(priority=2, groups="title")
	public void verifyHomePageTitle() throws InterruptedException{
		homePage = loginPage.LoginToFreeCRM(prop.getProperty("username"), prop.getProperty("password"));
		Thread.sleep(5000);
		extentTest = extent.startTest("verifyHomePageTitle");
		log.info("*******************verifying page title of FreeCRM***************************");
		String title = loginPage.getPageTitle();
		Assert.assertEquals(title, "CRMPRO");
	}
	
	@AfterMethod
public void tearDown(ITestResult result) throws IOException{
		
	if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+ result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			String screenshotPath = TestUtil.ScreenShot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}
		log.info("*************************Closing the report***********************");		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		driver.quit();
	}
	

}
