package com.qa.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.utils.TestUtils;

public class ApplicationsHomePage extends TestBase{

	//Object Repositories
	@FindBy(xpath="//a[@href='#/app/applications/home']")
	WebElement we_applications_home;
	
	@FindBy(xpath="//label[@class='ng-scope']")
	WebElement we_show;
	
	@FindBy(xpath="//input[contains(@type,'checkbox') and contains(@ng-model,'checked')]")
	WebElement we_checkboxs;
	
	@FindBy(xpath="//input[@type='checkbox']")
	WebElement we_checkbox;
	
	@FindBy(xpath="//div[@class='top-controls']//div[1]//div//button[3]")
	WebElement we_workflows;
	
	
	public ApplicationsHomePage(){
		PageFactory.initElements(driver, this);
	}
	
	public String verifyCurrentUrl(){
		return driver.getCurrentUrl();
	}
	
	
	public void getAllCheckBox(){
		List<WebElement> we_checkbox_list = TestUtils.getWebElements("xpath", "//input[@type='checkbox']");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (WebElement webElement : we_checkbox_list) {
			System.out.println("CheckBox Text: "+webElement.getText());
		}		
	}
	
	//TBD
	public void verifyAllCheckedCheckBoxes(){
		
		//execute ssh comand 
		//1. ssh
		//2. cmd execute : spark-submit --class org.apache.spark.examples.SparkPi --master local --num-executors 1 --driver-memory 2g --executor-memory 4g --executor-cores 1 /opt/cloudera/parcels/SPARK2-2.3.0.cloudera3-1.cdh5.13.3.p0.458809/lib/spark2/examples/jars/spark-examples_2.11-2.3.0.cloudera3.jar 100
		//3. response check success 
		
		//SSH_Conn.sshConnection(Parameter.hostname, Parameter.username, Parameter.privateKey, Parameter.command);
		
		
		List<WebElement> we_list = TestUtils.getWebElements("xpath", "//input[contains(@type,'checkbox') and contains(@ng-model,'checked')]");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int size = we_list.size();
		System.out.println("Size of checked box web elements list :"+size);
		boolean isChecked=false;
		for(int i=0; i<size; i++){
			isChecked = we_list.get(i).isSelected();
			String checkBoxText = we_list.get(i).getText();
			System.out.println("CheckBox is: "+checkBoxText);
			if(!isChecked){
				System.out.println("The checkBox: "+checkBoxText+"is not checked");
			}
		}		
		if(!isChecked){
			System.out.println("All are checkbox are checked.");
		}
		
	}
	//click on workflows tab
	public WorkflowsPage clickOnWorkflows() {
		we_workflows.click();
		return new WorkflowsPage();
	}

	
}
