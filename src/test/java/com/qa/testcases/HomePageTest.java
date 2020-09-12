package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.utils.TestUtils;

public class HomePageTest extends TestBase {

	public LoginPage loginPage;
	public HomePage homePage;

	public HomePageTest() {
		super();
	}

	@BeforeClass
	public void setUp() {
		log.info("INFO: Calling setUp");
		initialisation();
		
		loginPage = new LoginPage();
		homePage = loginPage.clickOnSignIn();
	}

	
	@Test(priority=1)
	public void verifydashBoardEnable(){
		log.info("INFO: Calling verifydashBoardEnable");
		String section_title = homePage.getSectionTitle();
		System.out.println("section_title: "+section_title);
		Assert.assertEquals(section_title, TestUtils.OPERATIONS_TEXT);
		if(homePage.dashBoardEnable()){
			System.out.println("DashBoard Enabled!");			
		}
	}

	@Test(priority=2)
	public void verifygetLoggedUserName(){
		log.info("INFO: Calling verifygetLoggedUserName");
		String user_name = homePage.getLoggedUserName();
		System.out.println("Actual user name: "+user_name);
		Assert.assertEquals(user_name, prop.getProperty("user_name"));
	}
	
	@Test
	public void verifyAllTextOnDropDownMenuTest(){
		log.info("INFO: Calling verifyAllTextOnDropDownMenuTest");
		String[] actual = homePage.verifyAllTextOnDropDownMenu();
		System.out.println("DorpDown Menu text are:");
		for(String str: actual){
			System.out.println("Text:"+ str);
		}
		String[] expected = {"Manage","About","API Token","New Ux","Logout"};
		boolean bool =TestUtils.compareStringArrays(actual, expected);
		System.out.println("Booelan:"+bool);
		Assert.assertTrue(bool);
	}
	
	@Test(enabled=false)
	public void veifySearchText(){
		homePage.searchText(TestUtils.SPARK_APP);
		//org.openqa.selenium.ElementNotInteractableException: element not interactable
	}
	
	@Test(enabled=false)
	public void verifyLogoutUser(){
		System.out.println("In verifyLogoutUser ");
		homePage.logoutUser();
	}
	
	@AfterClass
	public void tearDown(){
		log.info("INFO: Calling tearDown");
		driver.quit();
	}

}
