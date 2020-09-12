package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.ApplicationsHomePage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.WorkflowsPage;

public class TC_WF_20_Test extends TestBase{

	public LoginPage loginPage;
	public HomePage homePage;
	public ApplicationsHomePage applicationHomePage;
	public WorkflowsPage workflowsPage;
		
	
	TC_WF_20_Test(){
		super();
	}
	
	@BeforeTest
	public void setUp(){
		initialisation();
		loginPage = new LoginPage();
		homePage = loginPage.clickOnSignIn();
		applicationHomePage = homePage.clickOnApplications();
		workflowsPage = applicationHomePage.clickOnWorkflows();
	}
	
	//call test methods using reference.
	
	@Test(priority=1)
	public void verifyCurrentUrlTest(){
		String actual = workflowsPage.verifyCurrentUrl();
		System.out.println("Current Url in Workflows page: "+actual);
		String expected = "http://tnode91.unraveldata.com:3000/#/app/applications/workflows";
		//String expected = "http://tnode60.unraveldata.com:3000/#/app/applications/home";
		
		if(actual.equals(expected)){
			System.out.println("Verified workflows page.");
		}
		Assert.assertEquals(actual, expected);	
		log.info("Verifying url in workflows page");
	}
	
}
