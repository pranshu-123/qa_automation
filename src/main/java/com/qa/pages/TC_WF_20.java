package com.qa.pages;

import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class TC_WF_20 extends TestBase{
	
	//Object Repository
	
	public TC_WF_20(){
		PageFactory.initElements(driver, this);
	}
	
	//Write methods for test

	public String verifyCurrentUrl(){
		return driver.getCurrentUrl();
	}
}
