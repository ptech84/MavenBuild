package com.freecrm.Pages;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.freecrm.TestBase.TestBase;

public class ContactsPage extends TestBase{
	
	@FindBy(xpath=".//*[@name='title']")
	WebElement titleDDL;
	
	@FindBy(xpath="//select[contains(@name,'cs_category')]")
	WebElement CategoryDDL;
	
	@FindBy(xpath=".//*[@id='first_name']")
	WebElement FirstNameText;

	@FindBy(xpath=".//*[@id='surname']")
	WebElement SurNameText;
	
	@FindBy(xpath=".//*[@id='middle_initial']")
	WebElement MiddleNameText;
	
	
	public ContactsPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void addNewContactDetails(String FirstName, String surName){
		FirstNameText.sendKeys(FirstName);
		SurNameText.sendKeys(surName);
	System.out.println("addNewContactDetails added");
	}
	
	public void addMoreNewContactDetails(String FirstName, String surName, String MiddleName){
		FirstNameText.sendKeys(FirstName);
		SurNameText.sendKeys(surName);
		MiddleNameText.sendKeys(MiddleName);
	System.out.println("*************addMoreNewContactDetails added***********");
	}
	}
