package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.utils.TestUtils;

public class HomePage extends TestBase {

	public LoginPage loginPage;
	public HomePage homePage;
	public ManagePage managePage;
	
	@FindBy(css = ".head-logo")
	WebElement we_head_logo;
	
	@FindBy(xpath="//*[@id=\"dashboard_tabs\"]/button[1]")
	WebElement we_dashboard;
	
	@FindBy(css=".section-title")
	WebElement we_section_title;
	
	@FindBy(css=".dropdown.login")
	WebElement we_dropdown_login; 
	
	@FindBy(xpath="//div[@id='dLabel']/a")
	WebElement we_loggedUser;
	
//	@FindBy(xpath="//ul[@class='dropdown-menu']")
//	WebElement we_dropdownmenu;
	
	@FindBy(xpath="//a[contains(text(),'Logout')]")
	WebElement we_logout;
	
	@FindBy(xpath="//*[@class='dropdown-menu']//a[@href='#/app/manage/daemons']")
	WebElement we_manage;
	
	//search text and button
	@FindBy(css = "input[id=q_exec_id_or_hadoop_user_name_or_name_cont]")
	WebElement we_search;
	
	@FindBy(css="#submit")
	WebElement we_search_btn;
	
	//link for all tabs
	@FindBy(xpath="//a[@href='#/app/operations/dashboard']")
	WebElement we_operations;
	
	@FindBy(xpath="//a[@href='#/app/applications/home']")
	WebElement we_applications_home;
	
	@FindBy(xpath="//a[@href='#/app/reports/operational/chargeback']")
	WebElement we_reports;
	
	@FindBy(xpath="//a[@href='#/app/api']")
	WebElement we_api;
	
	//Operation tabs
	@FindBy(xpath="//button[@href='#/app/operations/dashboard']")
	WebElement we_Dashboard;
	
	@FindBy(xpath="//button[@href='#/app/operations/charts/resources']")
	WebElement we_Usage_Details;
	
	//Application tabs
	@FindBy(xpath="//button[@href='#/app/applications/home']")
	WebElement we_All_Applications;
	
	@FindBy(xpath="//button[@href='#/app/applications/running']")
	WebElement we_Running_Applications;
	
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	
	}
	public boolean verifyHeadLogo(){		
		return we_head_logo.isDisplayed();
	}
	
	public String getSectionTitle(){
		return we_section_title.getText();
	}
	
	public Boolean dashBoardEnable(){
		return we_dashboard.isEnabled();
	}
	
	public String getLoggedUserName(){
		return we_loggedUser.getText();
	}
	
	public void  getLoggedUserNameOptions(){		
		we_loggedUser.click();		
	}
	//Click on manage
	public ManagePage clickOnManage(){
		getLoggedUserNameOptions();
		we_manage.click();
		return new ManagePage();
	}

	//Click on Applications tab.
	public ApplicationsHomePage clickOnApplications(){
		we_applications_home.click();
		return new ApplicationsHomePage();		
	}

	//click for search text
	public void clickSearchButton(){
		we_search_btn.click();
	}
	public void searchText(String searchStr){
		we_search.sendKeys(searchStr);
		clickSearchButton();
	}
	
	public void logoutUser(){
		getLoggedUserNameOptions();
		we_logout.click();
	}
	
	public void getLoggedUserDropDownText(){
		getLoggedUserNameOptions();
		we_manage.getText();		
	}
	
	public String[] verifyAllTextOnDropDownMenu(){
		getLoggedUserNameOptions();
		String[] e_text = null;
		if(TestUtils.isElementPresent("xpath", "//ul[@class='dropdown-menu']//a")){
			
			List<WebElement> we_list = TestUtils.getWebElements("xpath", "//ul[@class='dropdown-menu']//a");	
			int size = we_list.size();
			e_text = new String[size];
			int i=0;
			for(WebElement e : we_list){				
				e_text[i]=e.getText();
				//System.out.println("Element Text: "+ e_text[i]);
				i++;
			}
		}
		return e_text;
	}
	
}
