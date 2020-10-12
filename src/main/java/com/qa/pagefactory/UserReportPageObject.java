package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author Sarbashree Ray
 * All date User report related webelements of unravel ui
 * is present in this class. Wherever you need to access these page object
 * encapsulates  an instance of this class and access the members with that object.
 */

public class UserReportPageObject {

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[1]/h1")
    public List<WebElement> HeaderElement;

    @FindBy(xpath = "//div[@class='row']//a/span[text()='Schedule User Report']")
    public WebElement scheduleuserreportButton;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[1]/div[2]/input")
    public WebElement addScheduleName;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[1]/div[2]/input")
    public WebElement drop;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[1]/div")
    public WebElement closeWindow;


    /**
     * @param driver The driver that will be used to look up the elements
     */
    public UserReportPageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}

