package com.freecrm.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import com.freecrm.TestBase.TestBase;



public class TestUtil extends TestBase{

	public static long PageLoadTimeOut = 40;
	public static long ImplicitlyWaitTimeOut = 40;
	
	public static String ScreenShot(WebDriver driver, String ScreenshotName) throws IOException{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String dst = System.getProperty("user.dir")+ "test-output" + ScreenshotName + dateName + ".png";
		File td = new File(dst);
		FileUtils.copyFile(src, td);
		return dst;
		
	}
	
}
