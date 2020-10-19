package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author sarbashree ray
 * All date schedule date related webelements of unravel ui
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */
public class SchedulePageObject {

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select")
    public WebElement schedule;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[1]")
    public WebElement Daily;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[2]")
    public WebElement Sunday;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[3]")
    public WebElement Monday;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[4]")
    public WebElement Tuesday;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[5]")
    public WebElement Wednesday;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[6]")
    public WebElement Thursday;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[7]")
    public WebElement Friday;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[8]")
    public WebElement Saturday;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[9]")
    public WebElement Everytwoweeks;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[2]/select/option[10]")
    public WebElement Everymonths;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[3]/span/input")
    public WebElement timepicker;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[3]/span/div[2]")
    public WebElement timepickerdropdown;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div[2]/div[3]/div[3]/div[3]/span/div[2]/div/select[1]")
    public WebElement hoursRange;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[3]/span/div[2]/div/select[1]/option[25]")
    public WebElement twentythreehours;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div[2]/div[3]/div[3]/div[3]/span/div[2]/div/select[2]")
    public WebElement minutes;

    @FindBy(xpath = "//*[@id=\"topx-landing-page\"]/div/div[3]/div[3]/div[3]/span/div[2]/div/select[2]/option[61]")
    public WebElement Fiftynine;

    /**
     * @param driver The driver that will be used to look up the elements
     */
    public SchedulePageObject(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}


