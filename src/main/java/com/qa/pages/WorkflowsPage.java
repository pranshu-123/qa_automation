package com.qa.pages;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.utils.JavaScriptExecuter;
import com.qa.utils.TestUtils;

public class WorkflowsPage extends TestBase{
	
	//Object Repository	
	@FindBy(xpath="//div[@id='daterange']")
	WebElement we_daterange;
	
	@FindBy(xpath="//div[@class='ranges']//li[contains(text(),'Last 24 Hours')]")
	WebElement we_24hr;
	
	@FindBy(xpath="//div[@id='search_results']//tbody//tr[1]")
	WebElement we_firstrow;
	
	@FindBy(xpath="//div[@class='tablesorter-header-inner' and contains(text(),'Start Time')]")
	WebElement we_start_time;
	
	@FindBy(xpath="//div[contains(text(),'hive2-wf')]")
	WebElement we_hive2wf;
	
	@FindBy(xpath="//div[contains(text(),'map-reduce-long_running_wf')]")
	WebElement we_mrlongrunningwf;
	
	public WorkflowsPage(){
		PageFactory.initElements(driver, this);
	}
	
	public String verifyCurrentUrl(){
		return driver.getCurrentUrl();
	}
	
	public void clickOnDateRange(){
		we_daterange.click();

		TestUtils.waitThreadSleep(2000);
		
		//we_24hr.click();
		WebElement we_24hr = TestUtils.waitForElement("XPATH", "//div[@class='ranges']//li[@data-range-key='Last 24 Hours']");
		if(we_24hr.isDisplayed()){
			we_24hr.click();
		}
		
		TestUtils.waitThreadSleep(2000);
		
		WebElement el = TestUtils.waitForElement("XPATH", "//div[@id='search_results']//tr[@class='ng-scope']");
		if(el.isDisplayed()){
			System.out.println("Table displayed");
		}
		
	}
	
	public void getWorkflowsTableDataForHive2WF(){

		JavaScriptExecuter.clickOnElement(driver, we_hive2wf);
				
		Set<String> handles = TestUtils.getWindowHandles();
		System.out.println("Handles"+handles);
		
		String parentHandle = TestUtils.getWindowHandle();
		System.out.println("parentHandle"+parentHandle);

		String appId ="";
		
		Iterator<String> itr=handles.iterator();
		while(itr.hasNext()){
			String childHandle = itr.next();
			if(!parentHandle.equals(childHandle)){
				System.out.println("childHandle"+childHandle);
				
				driver.switchTo().window(childHandle);
				
				System.out.println("Child windows Title: "+driver.getTitle());
				TestUtils.waitThreadSleep(1000);				
				System.out.println(driver.getCurrentUrl());				
				TestUtils.waitThreadSleep(100);

				WebElement we_status = TestUtils.getWebElement("XPATH", "//div[@class='main']//div[@class='initial   Success W']//div[2]");
				System.out.println("Staus of Application: "+we_status.getText());
				Assert.assertEquals(we_status.getText(), "SUCCESS");
				
				WebElement we_appId = TestUtils.getWebElement("XPATH", "//h2[@class='ng-binding']//span");
				appId= we_appId.getText();
				System.out.println("Application ID:"+ appId);
				
				WebElement we_workflow = TestUtils.getWebElement("XPATH", "//div[@class='sub']//div[contains(text(),'workflow')]");
				Assert.assertEquals(we_workflow.getText(), "WORKFLOW");				
									
				WebElement we_owner = TestUtils.getWebElement("XPATH", "//div[@uib-tooltip='Owner']");
				Assert.assertEquals(we_owner.getText(), "unravel");
				
				WebElement we_queue = TestUtils.getWebElement("XPATH", "//div[contains(text(),'root.unravel')]");
				Assert.assertEquals(we_queue.getText(), "root.unravel");
				
				WebElement we_cluster = TestUtils.getWebElement("XPATH", "//div[contains(text(),'Cluster1')]");
				Assert.assertEquals(we_cluster.getText(), "Cluster1");
								
				WebElement we_duration = TestUtils.getWebElement("XPATH", "//div[contains(text(),'Duration')]");
				Assert.assertEquals(we_duration.getText(), "DURATION");
				
				WebElement we_dataio = TestUtils.getWebElement("XPATH", "//div[contains(text(),'Data I/O')]");
				Assert.assertEquals(we_dataio.getText(), "DATA I/O");
				
				WebElement we_apps = TestUtils.getWebElement("XPATH", "//div[contains(text(),'# of Apps')]");
				Assert.assertEquals(we_apps.getText(), "# OF APPS");
				
				WebElement we_navigation = TestUtils.getWebElement("XPATH", "//a[contains(text(),'Navigation')]");
				we_navigation.click();
				
				driver.close();
				
			}else{
				System.out.println("Parent window");
			}
			
		}
		driver.switchTo().window(parentHandle);
		
		WebElement we_search = TestUtils.getWebElement("XPATH", "//input[@id='q_exec_id_or_hadoop_user_name_or_name_cont']");
		we_search.sendKeys(appId);
		WebElement we_search_btn = TestUtils.getWebElement("XPATH", "//form[@id='job_search']//div");
		we_search_btn.click();
				
		TestUtils.clickTextInTable("XPATH", "//div[@id='chargeback_apps_tbl']", "WFI");
		
		
		Set<String> wfi_handles = TestUtils.getWindowHandles();
		System.out.println("Handles"+wfi_handles);
		
		String wfi_parentHandle = TestUtils.getWindowHandle();
		System.out.println("parentHandle"+wfi_parentHandle);
		
		String expectedUrl = "http://tnode91.unraveldata.com:3000/#/app/application/workflow?execId="+appId;
		
		Iterator<String> wfi_itr=wfi_handles.iterator();
		while(wfi_itr.hasNext()){
			String childHandle = wfi_itr.next();
			if(!wfi_parentHandle.equals(childHandle)){
				System.out.println("childHandle"+childHandle);
				
				driver.switchTo().window(childHandle);
				System.out.println(driver.getCurrentUrl());
				Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
				
				driver.close();
			}
		}
		//return back to parent window
		driver.switchTo().window(wfi_parentHandle);
		
	}
	
	public String verifyAllKPIs(String appId){
		WebElement we_status = TestUtils.getWebElement("XPATH", "//div[@class='main']//div[@class='initial   Success W']//div[2]");
		System.out.println("Staus of Application: "+we_status.getText());
		Assert.assertEquals(we_status.getText(), "SUCCESS");
		
		WebElement we_appId = TestUtils.getWebElement("XPATH", "//h2[@class='ng-binding']//span");
		appId= we_appId.getText();
		System.out.println("Application ID:"+ appId);
		
		WebElement we_workflow = TestUtils.getWebElement("XPATH", "//div[@class='sub']//div[contains(text(),'workflow')]");
		Assert.assertEquals(we_workflow.getText(), "WORKFLOW");				
						
		WebElement we_owner = TestUtils.getWebElement("XPATH", "//div[@uib-tooltip='Owner']");
		Assert.assertEquals(we_owner.getText(), "unravel");
		
		WebElement we_queue = TestUtils.getWebElement("XPATH", "//div[contains(text(),'root.unravel')]");
		Assert.assertEquals(we_queue.getText(), "root.unravel");
		
		WebElement we_cluster = TestUtils.getWebElement("XPATH", "//div[contains(text(),'Cluster1')]");
		Assert.assertEquals(we_cluster.getText(), "Cluster1");
						
		WebElement we_duration = TestUtils.getWebElement("XPATH", "//div[contains(text(),'Duration')]");
		Assert.assertEquals(we_duration.getText(), "DURATION");
		
		WebElement we_dataio = TestUtils.getWebElement("XPATH", "//div[contains(text(),'Data I/O')]");
		Assert.assertEquals(we_dataio.getText(), "DATA I/O");
		
		WebElement we_apps = TestUtils.getWebElement("XPATH", "//div[contains(text(),'# of Apps')]");
		Assert.assertEquals(we_apps.getText(), "# OF APPS");
		
		WebElement we_navigation = TestUtils.getWebElement("XPATH", "//a[contains(text(),'Navigation')]");
		we_navigation.click();
		
		return appId;
	}
	
	public void getWorkflowsTableDataForMRLongRunningWF(){
		
		JavaScriptExecuter.clickOnElement(driver, we_mrlongrunningwf);
				
		Set<String> handles = TestUtils.getWindowHandles();
		System.out.println("Handles"+handles);
		
		String parentHandle = TestUtils.getWindowHandle();
		System.out.println("parentHandle"+parentHandle);

		String appId ="";
		
		Iterator<String> itr=handles.iterator();
		while(itr.hasNext()){
			String childHandle = itr.next();
			if(!parentHandle.equals(childHandle)){
				System.out.println("childHandle"+childHandle);
				
				driver.switchTo().window(childHandle);
				
				System.out.println("Child windows Title: "+driver.getTitle());
				TestUtils.waitThreadSleep(1000);				
				System.out.println(driver.getCurrentUrl());				
				TestUtils.waitThreadSleep(100);				
				appId=verifyAllKPIs(appId);
				
				driver.close();
				
			}else{
				System.out.println("Parent window");
			}
			
		}
		driver.switchTo().window(parentHandle);
		
		
		WebElement we_search = TestUtils.getWebElement("XPATH", "//input[@id='q_exec_id_or_hadoop_user_name_or_name_cont']");
		we_search.sendKeys(appId);
		WebElement we_search_btn = TestUtils.getWebElement("XPATH", "//form[@id='job_search']//div");
		we_search_btn.click();
				
		TestUtils.clickTextInTable("XPATH", "//div[@id='chargeback_apps_tbl']", "WFI");
		
		
		Set<String> wfi_handles = TestUtils.getWindowHandles();
		System.out.println("Handles"+wfi_handles);
		
		String wfi_parentHandle = TestUtils.getWindowHandle();
		System.out.println("parentHandle"+wfi_parentHandle);
		
		String expectedUrl = "http://tnode91.unraveldata.com:3000/#/app/application/workflow?execId="+appId;
		
		Iterator<String> wfi_itr=wfi_handles.iterator();
		while(wfi_itr.hasNext()){
			String childHandle = wfi_itr.next();
			if(!wfi_parentHandle.equals(childHandle)){
				System.out.println("childHandle"+childHandle);
				
				driver.switchTo().window(childHandle);
				System.out.println(driver.getCurrentUrl());
				Assert.assertEquals(driver.getCurrentUrl(), expectedUrl);
				
				driver.close();
			}
		}
		//return back to parent window
		driver.switchTo().window(wfi_parentHandle);
	}
	
}
