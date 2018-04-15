package com.freecrm.testcases;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.freecrm.Pages.ContactsPage;
import com.freecrm.Pages.HomePage;
import com.freecrm.Pages.LoginPage;
import com.freecrm.TestBase.TestBase;
import com.freecrm.Utilities.TestUtil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ContactsTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	ExtentReports extent;
	ExtentTest extentTest;
	Logger log = Logger.getLogger(ContactsTest.class);

	
	
	public ContactsTest(){
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
		homePage = new HomePage();
		contactsPage = new ContactsPage();
	}
	
	
	@Test(priority=1, groups="Click on Contacts", enabled=true)
	public void clickOnNewContacts(){
		extentTest = extent.startTest("addNewContacts");
		homePage = loginPage.LoginToFreeCRM(prop.getProperty("username"), prop.getProperty("password"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		homePage.clickOnContactsTab();
	}
	
	@Parameters({"FirstName", "SurName"})
	@Test(priority=2, groups="add new contacts", enabled =true)
	public void addNewContacts(String SurName, String FirstName){
		extentTest = extent.startTest("addNewContacts");
		homePage = loginPage.LoginToFreeCRM(prop.getProperty("username"), prop.getProperty("password"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		homePage.clickOnContactsTab();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contactsPage.addNewContactDetails(FirstName, SurName);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	@Test(dataProvider="getTestData", priority=3, groups="add more new contacts")
	public void addMoreNewContacts(String FirstName, String surName, String MiddleName){
		extentTest = extent.startTest("addMoreNewContacts");
		homePage = loginPage.LoginToFreeCRM(prop.getProperty("username"), prop.getProperty("password"));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		homePage.clickOnContactsTab();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		contactsPage.addMoreNewContactDetails(FirstName, surName, MiddleName);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@DataProvider
	  public Object[][] getTestData(){
		  Object[][] data = TestUtil.getTestData("Sheet1");
		  return data;
		  
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
