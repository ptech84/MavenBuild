package com.freecrm.Pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.TestBase.TestBase;

public class LoginPage extends TestBase{
	
	@FindBy(xpath="//input[@name='username']")
	WebElement username;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement loginBtn;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
		public HomePage LoginToFreeCRM(String un, String pw){
			username.sendKeys(un);
			password.sendKeys(pw);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", loginBtn);
			loginBtn.click();	
			return new HomePage();
			
		}
		
		public String getPageTitle(){
			return driver.getTitle();
			
		}

}
