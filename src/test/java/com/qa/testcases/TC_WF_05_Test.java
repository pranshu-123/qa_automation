package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.ApplicationsHomePage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.WorkflowsPage;
import com.qa.parameters.Parameter;

public class TC_WF_05_Test extends TestBase{

	public LoginPage loginPage;
	public HomePage homePage;
	public ApplicationsHomePage applicationHomePage;
	public WorkflowsPage workflowsPage;
		
	
	TC_WF_05_Test(){
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
	

	@Test(priority=1)
	public void verifyCurrentUrlTest(){
		String actual = workflowsPage.verifyCurrentUrl();
		System.out.println("Current Url in Workflows page: "+actual);
		
		
		if(actual.equals(Parameter.workflow_url)){
			System.out.println("Verified workflows page.");
		}
		Assert.assertEquals(actual, Parameter.workflow_url);	
		log.info("Verifying url in workflows page");
	}
	
	@Test(priority=2)
	public void clickOnDateRangeTest(){
		workflowsPage.clickOnDateRange();
	}
	
	@Test(priority=3, enabled=true)
	public void getWorkflowsTableDataForMRLongRunningWF(){
		workflowsPage.getWorkflowsTableDataForMRLongRunningWF();
	}
	
//	@AfterClass
//	public void tearDown(){
//		log.info("INFO: Calling tearDown");
//		driver.quit();
//	}

}
