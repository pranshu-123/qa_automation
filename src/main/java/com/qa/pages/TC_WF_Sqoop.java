package com.qa.pages;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.utils.TestUtils;

public class TC_WF_Sqoop extends TestBase {

	// Object Repository
	@FindBy(xpath = "//div[@id='search_results']//tbody//tr[1]")
	WebElement we_firstrow;

	@FindBy(xpath = "//div[@class='tablesorter-header-inner' and contains(text(),'Start Time')]")
	WebElement we_start_time;

	@FindBy(xpath = "//div[contains(text(),'sqoop-wf')]")
	WebElement we_sqoopwf;

	@FindBy(xpath = "//div[@class='main']//div[@class='initial   Success W']//div[2]")
	WebElement we_status;

	@FindBy(xpath = "//h2[@class='ng-binding']//span")
	WebElement we_AppId;

	@FindBy(xpath = "//li[1]/div[2]/div[1][(@class='main ng-binding')]")
	WebElement we_workflowDuration;

	@FindBy(xpath = "//li[2]/div[2]/div[1][(@class='main ng-binding')] ")
	WebElement we_workflowDataIO;

	@FindBy(xpath = "//li[contains(@class,'wf-tasks')]/div[2]/div")
	WebElement we_workflowCompletedApps;

	@FindBy(xpath = "//div[@class='sub']/div[2]")
	WebElement we_workflowName;

	@FindBy(xpath = "//div[@class='sub']//div[contains(text(),'workflow')]")
	WebElement we_type;

	@FindBy(xpath = "//div[@uib-tooltip='Owner']")
	WebElement we_workflowOwnerName;

	@FindBy(xpath = "//div[contains(@uib-tooltip,'Queue')]/div[2]")
	WebElement we_workflowQueueName;

	@FindBy(xpath = "//div[contains(@uib-tooltip,'Cluster')]/div[2]")
	WebElement we_workflowCluster;

	public TC_WF_Sqoop() {
		PageFactory.initElements(driver, this);
	}

	// Write methods for test

	public String verifyCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public void selectWorkflow() {
		try {
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", we_sqoopwf);

		} catch (StaleElementReferenceException se) {
			System.out.println("StaleElementReferenceException recevied!!!");
		}
	}

	public void navigateToWorkflowPage() {

		Set<String> handles = TestUtils.getWindowHandles();
		System.out.println("Handles" + handles);

		String parentHandle = TestUtils.getWindowHandle();
		System.out.println("parentHandle" + parentHandle);

		String appId = "";

		Iterator<String> itr = handles.iterator();
		while (itr.hasNext()) {
			String childHandle = itr.next();
			if (!parentHandle.equals(childHandle)) {
				System.out.println("childHandle" + childHandle);

				driver.switchTo().window(childHandle);

				System.out.println("Child windows Title: " + driver.getTitle());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				System.out.println(driver.getCurrentUrl());

				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
	}

	public void getWorkflowStatus() {

		// WebElement we_status = TestUtils.getWebElement("XPATH",
		// "//div[@class='main']//div[@class='initial Success W']//div[2]");
		System.out.println("Staus of Application: " + we_status.getText());
		Assert.assertEquals(we_status.getText(), "SUCCESS");

	}

	public String getWorkflowAppId() {

		String appId = we_AppId.getText();
		System.out.println("Application ID:" + appId);
		Assert.assertNotNull(appId);
		return appId;

	}

	public void getWorkflowAppTotalDuration() {

		String duration = we_workflowDuration.getText();
		System.out.println("Total Duration: " + duration);
		Assert.assertNotNull(duration);
	}

	public void getWorkflowName() {

		String name = we_workflowName.getText();
		System.out.println("Workflow Name: " + name);
		Assert.assertEquals(name, "sqoop-wf");

	}

	public void getWorkflowType() {

		String type = we_type.getText();
		System.out.println("Workflow type is: " + type);
		Assert.assertEquals(type, "WORKFLOW");
	}

	public void getWorkflowOwnerName() {

		String owner_name = we_workflowOwnerName.getText();
		System.out.println("Workflow Owner name is: " + owner_name);
		Assert.assertEquals(owner_name, "unravel");
	}

	public void getWorkflowQueueName() {

		String queue_name = we_workflowQueueName.getText();
		System.out.println("Workflow Queue is: " + queue_name);
		Assert.assertEquals(queue_name, "root.unravel");

	}

	public void getWorkflowClusterName() {

		String cluster_name = we_workflowCluster.getText();
		System.out.println("Workflow cluster is: " + cluster_name);
		Assert.assertEquals(cluster_name, "Cluster1");

	}

	public void getWorkflowDataIO() {

		String dataIO = we_workflowDataIO.getText();
		System.out.println("Total read and write bytes: " + dataIO);
		Assert.assertNotNull(dataIO);

	}

	public void getCompletedApps() {

		String tasks = we_workflowCompletedApps.getText();
		System.out.println("Completed Tasks: " + tasks);
		Assert.assertEquals(tasks, "3");
		;

	}
}
