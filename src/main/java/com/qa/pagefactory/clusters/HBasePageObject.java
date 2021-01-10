package com.qa.pagefactory.clusters;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HBasePageObject {

    public HBasePageObject(WebDriver driver){ PageFactory.initElements(driver, this);}

    @FindBy(xpath = "//li[contains(@class,'active')]/ul[contains(@class,'sub-menu')]//li/span[contains(text(),'HBase')]")
    public WebElement hbaseTab;

    @FindBy(xpath = "//h1[contains(text(), 'HBase')]")
    public WebElement hbaseHeader;

}
