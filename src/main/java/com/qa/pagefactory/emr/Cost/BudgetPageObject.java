package com.qa.pagefactory.emr.cost;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BudgetPageObject {

    @FindBy(xpath ="//div//button")
     public WebElement CreateBudgetButton ;

    @FindBy(xpath ="//section//span[text()='Upcoming']")
    public WebElement UpcomingLink ;

    @FindBy(xpath ="//section//span[text()='Active']")
    public WebElement ActiveLink ;

    @FindBy(xpath ="//section//span[text()='Expired']")
    public WebElement ExpiredLink ;
    public BudgetPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
