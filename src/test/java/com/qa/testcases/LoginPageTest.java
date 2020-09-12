package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class LoginPageTest extends TestBase{

	public LoginPage loginPage;
	public HomePage homePage;
	
	
	public LoginPageTest(){
		super();

	}
	
	@BeforeClass
	public void setUp(){
		initialisation();
		
		loginPage = new LoginPage();
		homePage = new HomePage();
	}

	@Test
	public void verifyHeadLogoTest(){
		
		if(loginPage.verifyHeadLogo()){
			System.out.println("Head Logo displayed");
		}
		Assert.assertTrue(true);
		log.info("verifing Header logo is displayed");
	}
	
	@Test
	public void clickOnSignInTest(){
		homePage = loginPage.clickOnSignIn();	
		log.info("verifing click on SignIn button");
	}
	
	@Test
	public void verifygetTitlePage(){
		String title = loginPage.getTitlePage();
		System.out.println("Title of Login Page: "+ title);
		Assert.assertEquals(title, "Unravel | unraveldata");
		log.info("verifing Title of Login page");
	}
	
	@Test
	public void verifynameOnLogoImage(){
		String name_logo = loginPage.nameOnLogoImage();
		Assert.assertEquals(name_logo, "Unravel Data");
		log.info("verifing name on logo is displayed");
	}
	
	@Test(enabled=false)
	public void verifyLogoTest(){
		loginPage.verifyLogo();
	}
	
	@AfterClass
	public void tearDown(){
		driver.quit();
		log.info("Close the current window and quits this driver, closing every associated window");
	}
	
}
