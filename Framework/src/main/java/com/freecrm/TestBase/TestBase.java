package com.freecrm.TestBase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import com.freecrm.Utilities.TestUtil;
import com.freecrm.testcases.LoginTest;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static ExtentReports extent;
	public static ExtentTest extentTest;
	public static Logger log = Logger.getLogger(TestBase.class);
	
	
	public TestBase(){
		
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/freecrm/config/properties/config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public static void Initialization(){
		String browserName = prop.getProperty("browser");
		if(browserName.equals("ie")){
			System.setProperty("webdriver.ie.driver", "C:/Selenium/BrowserDrivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			log.info("**************Launching IE Browser****************");
		} 
		else if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:/Selenium/BrowserDrivers/chromedriver.exe");
			driver = new ChromeDriver();
			log.info("**************Launching Chrome Browser****************");
		} 
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", "C:/Selenium/BrowserDrivers/geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("**************Launching Firefox Browser****************");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PageLoadTimeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.ImplicitlyWaitTimeOut, TimeUnit.SECONDS);
		log.info("**************Launching FreeCRM application****************");
		driver.get(prop.getProperty("url"));
		
	}
	


	public static void ExtentReports(ITestResult result) throws IOException{
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "Test Case failed" + result.getName());
			extentTest.log(LogStatus.FAIL, "Test case failed" + result.getThrowable());
		String screenShotPath= TestUtil.ScreenShot(driver, result.getName());
		extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenShotPath));
		} else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test case passed" + result.getName());			
		}
		
	}
	
	
}
