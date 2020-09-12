package com.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.utils.TestUtils;

public class ManagePage extends TestBase{

	//Object Repository
	@FindBy(css=".title")
	WebElement we_title;
	
	@FindBy(xpath="//li[@class='active']//a")
	WebElement we_Daemons;
	
	@FindBy(xpath="//div[@id='manage-page']//ul//li[2]")
	WebElement we_Stats;
	
	@FindBy(xpath="//div[@id='daemons']//table//thead//tr[1]")
	WebElement we_daemons_head_tr1;
//	
	@FindBy(xpath="//div[@id='daemons']//table//thead//tr[2]")
	WebElement we_daemons_head_tr2;
	
	
	public ManagePage(){
		PageFactory.initElements(driver, this);		
	}
	
	public String verifyTitle(){
		return we_title.getText();
	}
	
	public boolean verifyDaemonsTabSelected(){
		return we_Daemons.isDisplayed();
	}
	
	public boolean verifyStatsTabDisplays(){
		return we_Stats.isSelected();
	}
		
	//Get all tabs of Manage 
	public String[] getAllTabsOnManage(){
		//driver.findElements(By.xpath(""//ul[@id='settings-nav-tab']//a""))
		List<WebElement> we_list = TestUtils.getWebElements("xpath", "//ul[@id='settings-nav-tab']//a");
		
		String[] actual= null;
		
		if(we_list.size() > 0){
			int size = we_list.size();
			actual = new String[size];
			
			int i=0;
			for(WebElement e: we_list){
				actual[i] = e.getText();
				System.out.println("Text: "+actual[i]);
				i++;
			}
		}
		return actual;		
	}
		
	public String getDaemons_tr1_Text(){		
		return we_daemons_head_tr1.getText();
	}
	
	public String getDaemons_tr2_Text(){		
		return we_daemons_head_tr2.getText();		
	}
}
