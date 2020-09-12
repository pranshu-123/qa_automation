package com.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.ApplicationsHomePage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;
import com.qa.pages.TC_WF_Sqoop;
import com.qa.pages.WorkflowsPage;

public class TC_WF_Sqoop_Test extends TestBase {

	public LoginPage loginPage;
	public HomePage homePage;
	public ApplicationsHomePage applicationHomePage;
	public WorkflowsPage workflowsPage;
	public TC_WF_Sqoop sqoopPage;

	TC_WF_Sqoop_Test() {
		super();
	}

	@BeforeTest
	public void setUp() {
		initialisation();
		loginPage = new LoginPage();
		homePage = loginPage.clickOnSignIn();
		applicationHomePage = homePage.clickOnApplications();
		workflowsPage = applicationHomePage.clickOnWorkflows();
		sqoopPage = new TC_WF_Sqoop();
	}

	// call test methods using reference.

	@Test(priority = 1)
	public void verifyCurrentUrlTest() {
		String actual = workflowsPage.verifyCurrentUrl();
		System.out.println("Current Url in Workflows page: " + actual);
		String expected = "http://tnode91.unraveldata.com:3000/#/app/applications/workflows";
		// String expected =
		// "http://tnode60.unraveldata.com:3000/#/app/applications/home";

		if (actual.equals(expected)) {
			System.out.println("Verified workflows page.");
		}
		Assert.assertEquals(actual, expected);
		// log.info("Verifying url in workflows page");
	}

	@Test(priority = 2)
	public void clickOnDateRangeTest() {
		workflowsPage.clickOnDateRange();
	}

	@Test(priority = 3)
	public void verifyWorkflowPage() {

		sqoopPage.selectWorkflow();
		sqoopPage.navigateToWorkflowPage();
		// workflowsPage.getWorkflowsTableData();
	}

	@Test(priority = 4)
	public void verifyWorkflowType() {

		sqoopPage.getWorkflowType();
	}

	@Test(priority = 5)
	public void verifyWorkflowNamed() {

		sqoopPage.getWorkflowName();
	}

	@Test(priority = 6)
	public void verifyWorkflowStatus() {

		sqoopPage.getWorkflowStatus();
	}

	@Test(priority = 7)
	public void verifyWorkflowAppId() {

		sqoopPage.getWorkflowAppId();
	}

	@Test(priority = 8)
	public void verifyWorkflowDuration() {

		sqoopPage.getWorkflowAppTotalDuration();
	}

	@Test(priority = 9)
	public void verifyWorkflowDataIO() {
		sqoopPage.getWorkflowDataIO();
	}

	@Test(priority = 10)
	public void verifyWorkflowCompletedTasks() {
		sqoopPage.getCompletedApps();
	}

	@Test(priority = 11)
	public void verifyWorkflowOwnerName() {
		sqoopPage.getWorkflowOwnerName();
	}

	@Test(priority = 12)
	public void verifyWorkflowQueueName() {
		sqoopPage.getWorkflowQueueName();
	}

	@Test(priority = 13)
	public void verifyWorkflowCluster() {
		sqoopPage.getWorkflowClusterName();
	}

}

