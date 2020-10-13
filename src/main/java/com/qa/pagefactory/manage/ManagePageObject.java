package com.qa.pagefactory.manage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.text.html.CSS;
import java.util.List;

/**
 * @author Birender Kumar
 */
public class ManagePageObject {

    @FindBy(xpath="//ul[contains(@class,'primary-links')]//li[7]//ul//li//span")
    public List<WebElement> allManageTabList;

    @FindBy(css = "div#daemons-template h1")
    public WebElement daemonsHeader;

    public ManagePageObject(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }
}
