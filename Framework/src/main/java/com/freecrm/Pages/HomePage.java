package com.freecrm.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.freecrm.TestBase.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//a[@title='Contacts']")
	WebElement contactsTab;
	
	@FindBy(xpath="//a[contains(@title,'New Contact')]")
	WebElement newContactTab;		
	
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}

	
	
	public ContactsPage clickOnContactsTab(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.switchTo().frame("mainpanel");
		Actions act = new Actions(driver);
		act.moveToElement(contactsTab).build().perform();
		newContactTab.click();
		return new ContactsPage();
	}
}
