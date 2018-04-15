package com.freecrm.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.freecrm.TestBase.TestBase;



public class TestUtil extends TestBase{
	
	public static String TESTDATA_SHEET_PATH = "C:/Users/e3009192/git/MavenBuild/Framework/src/main/resources/TestData.xlsx";

	public static long PageLoadTimeOut = 40;
	public static long ImplicitlyWaitTimeOut = 40;
	static Workbook book;
	static Sheet sheet;
	
	public static String ScreenShot(WebDriver driver, String ScreenshotName) throws IOException{
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String dst = System.getProperty("user.dir")+ "test-output" + ScreenshotName + dateName + ".png";
		File td = new File(dst);
		FileUtils.copyFile(src, td);
		return dst;
		
	}
	
	
	public static Object[][] getTestData(String sheetName){
				FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
			Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];//object[4][2]
			for (int i = 0; i < sheet.getLastRowNum(); i++) {
				for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				}
			}
			return data;
		}
}
