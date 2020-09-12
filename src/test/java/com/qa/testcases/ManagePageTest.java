package com.qa.testcases;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.ManagePage;
import com.qa.utils.TestUtils;

public class ManagePageTest extends TestBase{

	public LoginPage loginPage;
	public HomePage homePage;
	public ManagePage managePage;
	
	public ManagePageTest(){
		super();		
	}
	
	@BeforeTest
	public void setUp(){
		
		initialisation();
		loginPage = new LoginPage();
		homePage = loginPage.clickOnSignIn();
		managePage = homePage.clickOnManage();
		
		log.info("verifying setUp of ManagePageTest.");
	}
	
	@Test(priority=2)
	public void verifyTitleTest(){
		String actual = managePage.verifyTitle();
		Assert.assertEquals(actual, "MANAGE");	
		log.info("verifying Title of ManagePageTest.");
	}
	
	@Test
	public void verifyStatsTabDisplaysTest(){
		boolean bool = managePage.verifyStatsTabDisplays();
		Assert.assertFalse(bool);
		log.info("verifying Stats Tab is Displayed.");
	}
	
	@Test(priority=1)
	public void verifyDaemonsTabSelecetedTest(){
		boolean bool = managePage.verifyDaemonsTabSelected();
		Assert.assertTrue(bool);
	}
	
	
	@Test(priority=3)
	public void getAllTabsOnManageTest(){
		String[] actual = managePage.getAllTabsOnManage();
		String[] expected = {"Daemons","Stats","Run Diagnostics","Monitoring","Role Manager","Auto Actions"};
		Boolean bool = TestUtils.compareStringArrays(actual, expected);
		Assert.assertTrue(bool);
	}
	
	@Test
	public void getDaemons_tr1_TextTest(){
		String actual =managePage.getDaemons_tr1_Text();
		System.out.println("Daemons head row1 text:"+ actual);
		String expected= "DAEMONS LAST UPDATE MEMORY TOTAL GC GC TIME UPTIME MESSAGE COUNT";
		boolean bool = actual.equals(expected);
		Assert.assertTrue(bool);		
	}
	
	@Test
	public void getDaemons_tr2_TextTest(){
		String actual = managePage.getDaemons_tr2_Text();
		System.out.println("Daemons head row2 text:"+ actual);
		String expected= "DATE TIME TIME CURRENT MAX USAGE % INFO PROGRESS ERROR FATAL";
		boolean bool = actual.equals(expected);
		Assert.assertTrue(bool);		
	}
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	
	
}
