package com.qa.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.base.TestBase;
import com.qa.utils.TestUtils;

public class LoginPage extends TestBase{

	//Webelement Object Repository
	@FindBy(id="user_login")
	WebElement we_username;
	
	@FindBy(id="user_password")
	WebElement we_password;
	
	@FindBy(xpath="//*[@id=\"new_user\"]/div[2]/div[3]/input")
	WebElement we_signin;
	
	@FindBy(xpath="/html/body/div[1]/ui-view/login-form/header/div[4]/div/div/a/div")
	WebElement we_imgName;
	
	@FindBy(css = ".head-logo")
	WebElement we_headLogo;
		
	@FindBy(css=".lg")
	WebElement we_logo;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String getTitlePage(){
		return driver.getTitle();
	}
	
	public Boolean verifyHeadLogo(){
		return we_headLogo.isDisplayed();
	}
	
	//Logo image compare
	public void verifyLogo(){
		String expectedImagefilepath = "D:\\java_tut\\Eclipse_wrk\\UnravelDataUIProject\\screenshots\\expectedImages\\unravel_new_logo.png";
		TestUtils.compareImage(we_logo, expectedImagefilepath);
	}
	
	public String nameOnLogoImage(){
		return we_imgName.getText();
	}
	
	
	public HomePage clickOnSignIn(){
		String user = prop.getProperty("user_name");		
		we_username.sendKeys(user);
		String pwd = prop.getProperty("password");
		we_password.sendKeys(pwd);
		we_signin.click();
		return new HomePage();
	}
	
	//Click SignIn using Keyborad ENTER key.
	public void clickOnSignInByEnterKey(){
		String user = prop.getProperty("user_name");		
		we_username.sendKeys(user);
		String pwd = prop.getProperty("password");
		we_password.sendKeys(pwd);
		we_signin.sendKeys(Keys.ENTER);
	}
}
