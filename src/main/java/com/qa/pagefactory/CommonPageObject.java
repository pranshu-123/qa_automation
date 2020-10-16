package com.qa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/** @author Ankur Jaiswal
 * Any common element which is used across pages
 * is present in this class. Wherever you need to access these page object
 * create an instance of this class and access the members with that object.
 */

public class CommonPageObject {

  @FindBy(xpath = "//li[contains(@class,'select2-results__option')]")
  public List<WebElement> clustersList;

  @FindBy(xpath = "//span[contains(@class,'select2-selection__arrow')]")
  public WebElement clusterDropdown;

  @FindBy(xpath="//span[contains(@class, 'select2-search--dropdown')]/input")
  public WebElement clusterSearchBox;

  @FindBy(xpath="//span[@class='select2-results']//li")
  public WebElement clusterSearchFirstField;

  @FindBy(xpath = "//span[contains(@class,'select2-dropdown--below')]//li")
  public List<WebElement> listOfClusters;

  @FindBy(xpath = "//div[contains(@class,'close pointer')]")
  public List<WebElement> closeModalButton;

  public CommonPageObject(WebDriver driver) {
    PageFactory.initElements(driver,this);
  }
}
